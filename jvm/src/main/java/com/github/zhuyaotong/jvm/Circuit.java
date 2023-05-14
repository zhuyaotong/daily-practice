package com.github.zhuyaotong.jvm;

import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.invoke.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class Horse {
  public void race() {
    System.out.println("Horse.race()");
  }
}

class Deer {
  public void race() {
    System.out.println("Deer.race()");
  }
}

// javac Circuit.java
// java Circuit
public class Circuit {

  public static void startRace(Object obj) {
    // aload obj
    // invokedynamic race()
  }

  public static void main(String[] args) {
    startRace(new Horse());
    // startRace(new Deer());
  }

  public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType)
      throws Throwable {

    MethodHandle mh = l.findVirtual(Horse.class, name, MethodType.methodType(void.class));

    return new ConstantCallSite(mh.asType(callSiteType));
  }
}

class ASMHelper implements Opcodes {

  private static class MyMethodVisitor extends MethodVisitor {

    private static final String BOOTSTRAP_CLASS_NAME = Circuit.class.getName().replace('.', '/');
    private static final String BOOTSTRAP_METHOD_NAME = "bootstrap";
    private static final String BOOTSTRAP_METHOD_DESC =
        MethodType.methodType(
                CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class)
            .toMethodDescriptorString();

    private static final String TARGET_METHOD_NAME = "race";
    private static final String TARGET_METHOD_DESC = "(Ljava/lang/Object;)V";

    public final MethodVisitor mv;

    public MyMethodVisitor(int api, MethodVisitor mv) {
      super(api);
      this.mv = mv;
    }

    @Override
    public void visitCode() {
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);

      Handle h =
          new Handle(
              H_INVOKESTATIC,
              BOOTSTRAP_CLASS_NAME,
              BOOTSTRAP_METHOD_NAME,
              BOOTSTRAP_METHOD_DESC,
              false);

      mv.visitInvokeDynamicInsn(TARGET_METHOD_NAME, TARGET_METHOD_DESC, h);
      mv.visitInsn(RETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
  }

  public static void main(String[] args) throws IOException {
    ClassReader cr = new ClassReader("Circuit");
    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);

    ClassVisitor cv =
        new ClassVisitor(ASM6, cw) {

          @Override
          public MethodVisitor visitMethod(
              int access, String name, String descriptor, String signature, String[] exceptions) {

            MethodVisitor visitor =
                super.visitMethod(access, name, descriptor, signature, exceptions);

            if ("startRace".equals(name)) {
              return new MyMethodVisitor(ASM6, visitor);
            }

            return visitor;
          }
        };

    cr.accept(cv, ClassReader.SKIP_FRAMES);

    Files.write(Paths.get("Circuit.class"), cw.toByteArray());
  }
}

// 需要更改ASMHelper.MyMethodVisitor中的BOOTSTRAP_CLASS_NAME
class MonomorphicInlineCache {

  private final MethodHandles.Lookup lookup;
  private final String name;

  public MonomorphicInlineCache(MethodHandles.Lookup lookup, String name) {
    this.lookup = lookup;
    this.name = name;
  }

  private Class<?> cachedClass = null;
  private MethodHandle mh = null;

  public void invoke(Object receiver) throws Throwable {
    if (cachedClass != receiver.getClass()) {
      cachedClass = receiver.getClass();
      mh = lookup.findVirtual(cachedClass, name, MethodType.methodType(void.class));
    }

    mh.invoke(receiver);
  }

  public static CallSite bootstrap(MethodHandles.Lookup l, String name, MethodType callSiteType)
      throws Throwable {

    MonomorphicInlineCache ic = new MonomorphicInlineCache(l, name);
    MethodHandle mh =
        l.findVirtual(
            MonomorphicInlineCache.class,
            "invoke",
            MethodType.methodType(void.class, Object.class));

    return new ConstantCallSite(mh.bindTo(ic));
  }
}

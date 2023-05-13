package com.github.zhuyaotong;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class Singleton {
  private Singleton() {}

  private static class LazyHolder {
    static final Singleton INSTANCE = new Singleton();

    static {
      System.out.println("LazyHolder.<clinit>");
    }
  }

  public static Object getInstance(boolean flag) {
    if (flag) return new LazyHolder[2];
    return LazyHolder.INSTANCE;
  }

  public static void main(String[] args) {
    getInstance(true);
    System.out.println("----");
    getInstance(false);
  }
}

class Invoke {
  void invoke(Object obj, Object... args) {
    System.out.println("invoke(Object obj, Object... args)");
  }

  void invoke(String s, Object obj, Object... args) {
    System.out.println("invoke(String s, Object obj, Object... args)");
  }

  public static void main(String[] args) {
    Invoke invoke = new Invoke();

    invoke.invoke(null, 1); // 调用第二个invoke方法

    invoke.invoke(null, 1, 2); // 调用第二个invoke方法

    invoke.invoke(null, new Object[] {1}); // 只有手动绕开可变长参数的语法糖，才能调用第一个invoke方法
  }
}

interface Customer {
  boolean isVIP();
}

class Merchant {
  public Number actionPrice(double price, Customer customer) {
    return null;
  }
}

class NaiveMerchant extends Merchant {
  @Override
  public Double actionPrice(double price, Customer customer) {
    return null;
  }
}

abstract class Passenger {

  abstract void passThroughImmigration();

  public static void main(String[] args) {
    Passenger a = new ChinesePassenger();
    Passenger b = new ForeignerPassenger();
    long current = System.currentTimeMillis();

    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);

        current = temp;
      }

      Passenger c = (i < 1_000_000_000) ? a : b;
      c.passThroughImmigration();
    }
  }
}

class ChinesePassenger extends Passenger {

  @Override
  void passThroughImmigration() {}
}

class ForeignerPassenger extends Passenger {

  @Override
  void passThroughImmigration() {}
}

class Foo {
  private int tryBlock;
  private int catchBlock;
  private int finallyBlock;
  private int methodExit;

  public void test() {
    try {
      tryBlock = 0;
    } catch (Exception e) {
      catchBlock = 1;
    } finally {
      finallyBlock = 2;
    }
    methodExit = 3;
  }
}

class Foo2 implements AutoCloseable {

  private final String name;

  public Foo2(String name) {
    this.name = name;
  }

  @Override
  public void close() {
    throw new RuntimeException(name);
  }

  public static void main(String[] args) {
    try (Foo2 foo0 = new Foo2("Foo0"); // try-with-resources
        Foo2 foo1 = new Foo2("Foo1");
        Foo2 foo2 = new Foo2("Foo2")) {

      throw new RuntimeException("Initial");
    }
  }
}

class Foo3 {
  private int tryBlock;
  private int catchBlock;
  private int finallyBlock;
  private int methodExit;

  public void test() {
    for (int i = 0; i < 100; i++) {
      try {
        tryBlock = 0;
        if (i < 50) {
          continue;
        } else if (i < 80) {
          break;
        } else {
          return;
        }
      } catch (Exception e) {
        catchBlock = 1;
      } finally {
        finallyBlock = 2;
      }
    }
    methodExit = 3;
  }
}

class Test {

  public static void target(int i) {
    new Exception("#" + i).printStackTrace();
  }

  public static void main(String[] args) throws Exception {
    Class<?> klass = Class.forName("com.github.zhuyaotong.Test");

    Method method = klass.getMethod("target", int.class);

    method.invoke(null, 0);
  }
}

class Test2 {

  public static void target(int i) {
    new Exception("#" + i).printStackTrace();
  }

  public static void main(String[] args) throws Exception {

    Class<?> klass = Class.forName("com.github.zhuyaotong.Test2");

    Method method = klass.getMethod("target", int.class);

    for (int i = 0; i < 20; i++) {
      method.invoke(null, i);
    }
  }
}

class Test3 {

  public static void target(int i) {
    // 空方法
  }

  public static void main(String[] args) throws Exception {
    Class<?> klass = Class.forName("com.github.zhuyaotong.Test3");
    Method method = klass.getMethod("target", int.class);

    long current = System.currentTimeMillis();

    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
        current = temp;
      }

      method.invoke(null, 128);
    }
  }
}

class Test4 {

  public static void target(int i) {
    // 空方法
  }

  public static void main(String[] args) throws Exception {
    Class<?> klass = Class.forName("com.github.zhuyaotong.Test4");
    Method method = klass.getMethod("target", int.class);

    Object[] arg = new Object[1]; // 在循环外构造参数数组
    arg[0] = 128;

    long current = System.currentTimeMillis();
    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
        current = temp;
      }

      method.invoke(null, arg);
    }
  }
}

// 在运行指令中添加如下两个虚拟机参数：
// -Djava.lang.Integer.IntegerCache.high=128
// -Dsun.reflect.noInflation=true
class Test5 {
  public static void target(int i) {
    // 空方法
  }

  public static void main(String[] args) throws Exception {
    Class<?> klass = Class.forName("com.github.zhuyaotong.Test5");
    Method method = klass.getMethod("target", int.class);
    method.setAccessible(true); // 关闭权限检查

    long current = System.currentTimeMillis();
    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
        current = temp;
      }

      method.invoke(null, 128);
    }
  }
}

class Test6 {

  public static void target(int i) {
    // 空方法
  }

  public static void main(String[] args) throws Exception {
    Class<?> klass = Class.forName("com.github.zhuyaotong.Test6");
    Method method = klass.getMethod("target", int.class);
    method.setAccessible(true); // 关闭权限检查
    polluteProfile();

    long current = System.currentTimeMillis();
    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
        current = temp;
      }

      method.invoke(null, 128);
    }
  }

  public static void polluteProfile() throws Exception {
    Method method1 = Test6.class.getMethod("target1", int.class);
    Method method2 = Test6.class.getMethod("target2", int.class);

    for (int i = 0; i < 2000; i++) {
      method1.invoke(null, 0);
      method2.invoke(null, 0);
    }
  }

  public static void target1(int i) {}

  public static void target2(int i) {}
}

class Foo4 {
  public static void bar(Object o) {
    new Exception().printStackTrace();
  }

  public static void main(String[] args) throws Throwable {
    MethodHandles.Lookup l = MethodHandles.lookup();
    MethodType t = MethodType.methodType(void.class, Object.class);

    MethodHandle mh = l.findStatic(Foo4.class, "bar", t);
    mh.invokeExact(new Object());
  }
}

class Foo5 {

  public void bar(Object o) {}

  public static void main(String[] args) throws Throwable {
    MethodHandles.Lookup l = MethodHandles.lookup();
    MethodType t = MethodType.methodType(void.class, Object.class);
    MethodHandle mh = l.findVirtual(Foo5.class, "bar", t);

    long current = System.currentTimeMillis();
    for (int i = 1; i <= 2_000_000_000; i++) {

      if (i % 100_000_000 == 0) {
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
        current = temp;
      }

      mh.invokeExact(new Foo5(), new Object());
    }
  }
}

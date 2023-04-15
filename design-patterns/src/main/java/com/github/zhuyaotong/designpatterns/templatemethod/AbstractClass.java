package com.github.zhuyaotong.designpatterns.templatemethod;

public abstract class AbstractClass {
    public final void templateMethod() {
        //...
        method1();
        //...
        method2();
        //...
    }

    protected abstract void method1();

    protected abstract void method2();
}

class ConcreteClass1 extends AbstractClass {
    @Override
    protected void method1() {
        //...
    }

    @Override
    protected void method2() {
        //...
    }
}

class ConcreteClass2 extends AbstractClass {
    @Override
    protected void method1() {
        //...
    }

    @Override
    protected void method2() {
        //...
    }
}

interface ICallback {
    void methodToCallback();
}

class BClass {
    public void process(ICallback callback) {
        //...
        callback.methodToCallback();
        //...
    }
}

class AClass {
    public static void main(String[] args) {

        BClass b = new BClass();
        b.process(new ICallback() { //回调对象
            @Override
            public void methodToCallback() {
                System.out.println("Call back me.");
            }
        });
    }
}

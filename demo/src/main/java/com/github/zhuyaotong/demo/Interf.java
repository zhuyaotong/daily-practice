package com.github.zhuyaotong.demo;

public interface Interf {
    void f1();

    void f2();
}

class OriginalClass implements Interf {
    @Override
    public void f1() {
        //...
    }

    @Override
    public void f2() {
        //...
    }
}

class WrapperClass implements Interf {
    private OriginalClass oc;

    public WrapperClass(OriginalClass oc) {
        this.oc = oc;
    }

    @Override
    public void f1() {
        //...附加功能...
        this.oc.f1();
        //...附加功能...
    }

    @Override
    public void f2() {
        this.oc.f2();
    }
}
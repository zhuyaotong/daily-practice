package com.github.zhuyaotong.demo.refactoring;

public class Foo {
    Bar bar;

    public Foo getImpValue() {
        return bar.getImpValue();
    }
}

class Bar {
    private Foo impValue1;

    public Bar(Foo impValue) {
        impValue1 = impValue;
    }

    public Foo getImpValue() {
        return impValue1;
    }
}

class Client {
    Foo a;
    Foo impValue = a.getImpValue();
}



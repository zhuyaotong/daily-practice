package com.github.zhuyaotong.concurrency;

public class FinalFieldExample {

    // 以下代码来源于【参考1】
    final int x;
    final int y;

    private final static GlobalClass global = new GlobalClass();

    // 错误的构造函数
    public FinalFieldExample() {
        x = 3;
        y = 4;
        // 此处就是讲this逸出，
        global.obj = this;
    }
}

class GlobalClass {
    protected FinalFieldExample obj;
}

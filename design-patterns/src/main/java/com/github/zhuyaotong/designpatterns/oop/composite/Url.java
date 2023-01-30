package com.github.zhuyaotong.designpatterns.oop.composite;

public class Url {
    //...省略属性和方法
}

class Crawler {
    private Url url; // 组合

    public Crawler() {
        this.url = new Url();
    }
    //...
}

class PageAnalyzer {
    private Url url; // 组合

    public PageAnalyzer() {
        this.url = new Url();
    }
    //..
}

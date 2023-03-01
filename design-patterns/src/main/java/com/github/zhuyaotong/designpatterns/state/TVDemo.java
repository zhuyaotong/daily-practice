package com.github.zhuyaotong.designpatterns.state;

public class TVDemo {
    public static void main(String[] args) {
        TVContext tvContext = new TVContext();
        TVState start = new TVStartState();
        TVState stop = new TVStopState();
        tvContext.setTvState(start);
        tvContext.doAction();
        tvContext.setTvState(stop);
        tvContext.doAction();
    }
}

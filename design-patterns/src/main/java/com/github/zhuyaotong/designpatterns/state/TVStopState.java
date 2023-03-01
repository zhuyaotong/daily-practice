package com.github.zhuyaotong.designpatterns.state;

public class TVStopState implements TVState{
    @Override
    public void doAction() {
        System.out.println("TV is turned OFF.");
    }
}

package com.github.zhuyaotong.designpatterns.state;

public class TVStartState implements TVState{
    @Override
    public void doAction() {
        System.out.println("TV is turned ON.");
    }
}

package com.github.zhuyaotong.designpatterns.state;

public class TVContext implements TVState {
    private TVState tvState;

    @Override
    public void doAction() {
        this.tvState.doAction();
    }

    public void setTvState(TVState tvState) {
        this.tvState = tvState;
    }

    public TVState getTvState() {
        return tvState;
    }
}

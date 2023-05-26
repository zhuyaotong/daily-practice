package com.github.zhuyaotong.demo.state;

// 定义一个抽象的状态类
public abstract class State {

  Context context;

  public void setContext(Context context) {
    this.context = context;
  }

  public abstract void handle1();

  public abstract void handle2();
}

package com.github.zhuyaotong.demo.state;

// 定义一个上下文管理环境
public class Context {

  public static final ConcreteStateA contreteStateA = new ConcreteStateA();
  public static final ConcreteStateB contreteStateB = new ConcreteStateB();

  private State CurrentState;

  public State getCurrentState() {
    return CurrentState;
  }

  public void setCurrentState(State currentState) {
    this.CurrentState = currentState;
    this.CurrentState.setContext(this);
  }

  public void handle1() {
    this.CurrentState.handle1();
  }

  public void handle2() {
    this.CurrentState.handle2();
  }
}

package com.github.zhuyaotong.demo.state;

// 定义状态A
public class ConcreteStateA extends State {

  @Override
  public void handle1() {} // 本状态下必须要处理的事情

  @Override
  public void handle2() {
    super.context.setCurrentState(Context.contreteStateB); // 切换到状态B
    super.context.handle2(); // 执行状态B的任务
  }
}

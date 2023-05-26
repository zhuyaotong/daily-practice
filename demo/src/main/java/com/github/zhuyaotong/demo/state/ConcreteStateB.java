package com.github.zhuyaotong.demo.state;

// 定义状态B
public class ConcreteStateB extends State {

  @Override
  public void handle2() {} // 本状态下必须要处理的事情，...

  @Override
  public void handle1() {
    super.context.setCurrentState(Context.contreteStateA); // 切换到状态A
    super.context.handle1(); // 执行状态A的任务
  }
}

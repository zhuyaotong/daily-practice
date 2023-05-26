package com.github.zhuyaotong.demo.state;

// 定义client执行
public class Client {

  public static void main(String[] args) {
    Context context = new Context();
    context.setCurrentState(new ConcreteStateA());
    context.handle1();
    context.handle2();
  }
}

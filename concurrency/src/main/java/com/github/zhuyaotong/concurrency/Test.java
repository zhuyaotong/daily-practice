package com.github.zhuyaotong.concurrency;

public class Test {
  private static long count = 0;

  public static void main(String[] args) throws InterruptedException {
    //    System.out.println("count = " + count);
    //
    //    new Test().add10K();
    //    System.out.println("count = " + count);
    //
    //    count = 0;
    //    System.out.println("count = " + count);
    //
    //    calc();
    //    System.out.println("count = " + count);

    System.out.println(Test.calc());
  }

  private void add10K() {
    int idx = 0;

    while (idx++ < 10000) {
      count += 1;
    }
  }

  public static long calc() throws InterruptedException {
    final Test test = new Test();

    // 创建两个线程，执行add()操作
    Thread th1 = new Thread(test::add10K);
    Thread th2 = new Thread(test::add10K);

    // 启动两个线程
    th1.start();
    th2.start();

    // 等待两个线程执行结束
    th1.join();
    th2.join();

    return count;
  }
}

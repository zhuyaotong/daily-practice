package com.github.zhuyaotong.concurrency;

public class Singleton {
  static Singleton instance;

  static Singleton getInstance() {

    if (instance == null) {

      synchronized (Singleton.class) {
        if (instance == null) instance = new Singleton();
      }
    }

    return instance;
  }
}

// 以下代码来源于【参考1】
class VolatileExample {

  int x = 0;
  volatile boolean v = false;

  public void writer() {
    x = 42;
    v = true;
  }

  public void changeX() {

    synchronized (this) { // 此处自动加锁

      // x是共享变量,初始值=10
      if (this.x < 12) {
        this.x = 12;
      }
    } // 此处自动解锁
  }

  public void reader() {
    if (v == true) {
      // 这里x会是多少呢？
      System.out.println("reader方法里的x等于" + x);
    }
  }

  public static void main(String[] args) {
    VolatileExample ve = new VolatileExample();

    System.out.println("ve.x = " + ve.x);
    System.out.println("ve.v = " + ve.v);

    ve.writer();
    ve.reader();

    System.out.println("ve.x = " + ve.x);
    System.out.println("ve.v = " + ve.v);
  }
}

class X {

  // 修饰非静态方法
  synchronized void foo() {
    // 临界区
    // 业务逻辑
  }

  // 修饰静态方法
  static synchronized void bar() {
    // 临界区
  }

  // 修饰代码块
  Object obj = new Object();

  void baz() {
    synchronized (obj) {
      // 临界区
    }
  }
}

class SafeCalc {

  long value = 0L;

  synchronized long get() {
    return value;
  }

  synchronized void addOne() {
    value += 1;
  }
}

class SafeCalc2 {

  static long value = 0L;

  synchronized long get() {
    return value;
  }

  static synchronized void addOne() {
    value += 1;
  }
}

class SafeCalc3 {

  long value = 0L;

  long get() {
    synchronized (new Object()) {
      return value;
    }
  }

  void addOne() {
    synchronized (new Object()) {
      value += 1;
    }
  }
}

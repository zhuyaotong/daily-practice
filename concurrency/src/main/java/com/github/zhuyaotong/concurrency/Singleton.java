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

class Account {

  // 锁：保护账户余额（细粒度锁）
  private final Object balLock = new Object();

  // 账户余额
  private Integer balance;

  // 锁：保护账户密码（细粒度锁）
  private final Object pwLock = new Object();

  // 账户密码
  private String password;

  // 取款
  void withdraw(Integer amt) {

    synchronized (balLock) {
      if (this.balance > amt) {
        this.balance -= amt;
      }
    }
  }
  // 查看余额
  Integer getBalance() {
    synchronized (balLock) {
      return balance;
    }
  }

  // 更改密码
  void updatePassword(String pw) {
    synchronized (pwLock) {
      this.password = pw;
    }
  }

  // 查看密码
  String getPassword() {
    synchronized (pwLock) {
      return password;
    }
  }
}

class Account2 {

  private int balance;

  // 转账
  synchronized void transfer(Account2 target, int amt) {

    if (this.balance > amt) {
      this.balance -= amt;
      target.balance += amt;
    }
  }
}

class Account3 {
  private Object lock;
  private int balance;

  private Account3() {}

  // 创建Account时传入同一个lock对象
  public Account3(Object lock) {
    this.lock = lock;
  }

  // 转账
  void transfer(Account3 target, int amt) {

    // 此处检查所有对象共享的锁
    synchronized (lock) {
      if (this.balance > amt) {
        this.balance -= amt;
        target.balance += amt;
      }
    }
  }
}

class Account4 {
  private int balance;
  // 转账
  void transfer(Account4 target, int amt) {

    synchronized (Account4.class) {
      if (this.balance > amt) {
        this.balance -= amt;
        target.balance += amt;
      }
    }
  }
}

package com.github.zhuyaotong;

public class Singleton {
  private Singleton() {}

  private static class LazyHolder {
    static final Singleton INSTANCE = new Singleton();

    static {
      System.out.println("LazyHolder.<clinit>");
    }
  }

  public static Object getInstance(boolean flag) {
    if (flag) return new LazyHolder[2];
    return LazyHolder.INSTANCE;
  }

  public static void main(String[] args) {
    getInstance(true);
    System.out.println("----");
    getInstance(false);
  }
}

class Invoke {
  void invoke(Object obj, Object... args) {
    System.out.println("invoke(Object obj, Object... args)");
  }

  void invoke(String s, Object obj, Object... args) {
    System.out.println("invoke(String s, Object obj, Object... args)");
  }

  public static void main(String[] args) {
    Invoke invoke = new Invoke();

    invoke.invoke(null, 1); // 调用第二个invoke方法

    invoke.invoke(null, 1, 2); // 调用第二个invoke方法

    invoke.invoke(null, new Object[] {1}); // 只有手动绕开可变长参数的语法糖，才能调用第一个invoke方法
  }
}

interface Customer {
  boolean isVIP();
}

class Merchant {
  public Number actionPrice(double price, Customer customer) {
    return null;
  }
}

class NaiveMerchant extends Merchant {
  @Override
  public Double actionPrice(double price, Customer customer) {
    return null;
  }
}

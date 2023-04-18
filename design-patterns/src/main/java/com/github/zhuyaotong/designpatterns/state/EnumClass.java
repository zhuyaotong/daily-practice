package com.github.zhuyaotong.designpatterns.state;

public enum EnumClass {
  SMALL(0),
  SUPER(1),
  FIRE(2),
  CAPE(3);

  private int value;

  EnumClass(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }

  public static void main(String[] args) {
    System.out.println(EnumClass.SUPER.getValue());
  }
}

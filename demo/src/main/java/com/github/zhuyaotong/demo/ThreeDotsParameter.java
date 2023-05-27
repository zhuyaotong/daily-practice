package com.github.zhuyaotong.demo;

public class ThreeDotsParameter {

  public static void main(String[] args) {
    ThreeDotsParameter tdp = new ThreeDotsParameter();

    tdp.myMethod(); // Likely useless, but possible
    tdp.myMethod("one", "two", "three");
    tdp.myMethod("solo");
    tdp.myMethod("a", "b", "c");
  }

  public void myMethod(String... strings) {
    // method body
  }
}

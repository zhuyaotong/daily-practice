package com.github.zhuyaotong.demo;

public class Hello {
  public static void main(String[] args) {
    int c = 10;
    int d = 5;

    int result = c++ + ++c - --d - ++d + 1 + --c;
    //           10  + 12  - 4   - 5   + 1 + 11

    System.out.println("c=" + c);
    System.out.println("d=" + d);
    System.out.println("result=" + result);

    int x = 100;
    int y = x++;

    System.out.println("y=" + y);

    y = ++x;
    System.out.println("y=" + y);

    y = x--;
    System.out.println("y=" + y);

    y = --x;
    System.out.println("y=" + y);

    /* 以下为输出结果：
     y=100
     y=102
     y=102
     y=100
    */

    int a = 1;
    int b = a + ++a;
    System.out.println("b=" + b);
  }
}

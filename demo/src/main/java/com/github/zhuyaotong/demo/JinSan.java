package com.github.zhuyaotong.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class JinSan {
  public static void main(String[] args) throws Exception {
    //      new BufferedReader(new FileInputStream("a.dat"));
  }
}

class Solution10 {

  public static void main(String[] args) {

    List<String> strings = generateParenthesis(-3);

    System.out.println(strings);
  }

  public static List<String> generateParenthesis(int n) {

    List<String> res = new ArrayList<>();

    if (n <= 0) {
      return res;
    }

    // 肯定要先定义一个左括号
    int left = 1, right = 0;

    generateParenthesis(res, "(", n, left, right);

    return res;
  }

  private static void generateParenthesis(
      List<String> res, String out, int count, int left, int right) {

    if (right > left || left > count) {
      return;
    }

    if (left == right && left == count) {
      res.add(out);

    } else {
      generateParenthesis(res, out + '(', count, left + 1, right);
      generateParenthesis(res, out + ')', count, left, right + 1);
    }
  }
}

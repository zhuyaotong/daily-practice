package com.github.zhuyaotong.designpatterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorClass {

  public static void main(String[] args) {

    //    List<String> names = new ArrayList<>();
    //    names.add("a");
    //    names.add("b");
    //    names.add("c");
    //    names.add("d");
    //
    //    Iterator<String> iterator1 = names.iterator();
    //    Iterator<String> iterator2 = names.iterator();
    //
    //    iterator1.next();
    //    iterator1.remove();
    //    iterator2.next(); // 运行结果？

    List<Integer> list = new ArrayList<>();
    list.add(3);
    list.add(8);
    list.add(2);

    Iterator<Integer> iter1 = list.iterator(); // snapshot: 3, 8, 2

    list.remove(new Integer(2)); // list：3, 8

    Iterator<Integer> iter2 = list.iterator(); // snapshot: 3, 8

    list.remove(new Integer(3)); // list：8

    Iterator<Integer> iter3 = list.iterator(); // snapshot: 3

    // 输出结果：3 8 2
    while (iter1.hasNext()) {
      System.out.print(iter1.next() + " ");
    }
    System.out.println();

    // 输出结果：3 8
    while (iter2.hasNext()) {
      System.out.print(iter1.next() + " ");
    }
    System.out.println();

    // 输出结果：8
    while (iter3.hasNext()) {
      System.out.print(iter1.next() + " ");
    }
    System.out.println();
  }
}

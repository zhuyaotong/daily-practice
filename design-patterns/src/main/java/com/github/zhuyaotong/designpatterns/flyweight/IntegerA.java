package com.github.zhuyaotong.designpatterns.flyweight;

public class IntegerA {
    public static void main(String[] args) {

        Integer i1 = 56;
        Integer i2 = 56;
        Integer i3 = 129;
        Integer i4 = 129;
        Integer i5 = 127;
        Integer i6 = 127;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);

        String s1 = "小争哥";
        String s2 = "小争哥";
        String s3 = new String("小争哥");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}

package com.github.zhuyaotong.p3c;

import java.math.BigDecimal;

public class FloatingPointNumber {

    public static void main(String[] args) {
//        float a = 1.0F - 0.9F;
//        float b = 0.9F - 0.8F;
//        if (a == b) {
//            // 预期进入此代码块，执行其它业务逻辑
//            // 但事实上 a == b 的结果为 false
//            System.out.println("a == b");
//        }
//        Float x = Float.valueOf(a);
//        Float y = Float.valueOf(b);
//        if (x.equals(y)) {
//            // 预期进入此代码块，执行其它业务逻辑
//            // 但事实上 equals 的结果为 false
//            System.out.println("x.equals(y)");
//        }

//        float a = 1.0F - 0.9F;
//        float b = 0.9F - 0.8F;
//        float diff = 1e-6F;
//        if (Math.abs(a - b) < diff) {
//            System.out.println("true");
//        }

        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.compareTo(y) == 0) {
            System.out.println("true");
        }
    }

}

class StringClass {
    public static void main(String[] args) {
//        String str = "a,b,c,,";
//        String[] ary = str.split(",");
//        // 预期大于 3，结果等于 3
//        System.out.println(ary.length);

        String str = "start";
        for (int i = 0; i < 100; i++) {
            str = str + "hello";
        }
    }

//    public Integer getData() {
//        if (condition) {
//            return this.data + 100;
//        } else {
//            return this.data - 100;
//        }
//    }
}
package com.github.zhuyaotong.demo.reflect;

import java.lang.reflect.*;

public class ChangingValuesOfFields {
    public double d;

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("com.github.zhuyaotong.demo.reflect.ChangingValuesOfFields");
            Field fld = cls.getField("d");

            ChangingValuesOfFields f2obj = new ChangingValuesOfFields();
            System.out.println("d = " + f2obj.d);

            fld.setDouble(f2obj, 12.34);
            System.out.println("d = " + f2obj.d);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}

package com.github.zhuyaotong.demo.reflect;

import java.lang.reflect.*;

public class CreatingNewObjects {
    public CreatingNewObjects() {
    }

    public CreatingNewObjects(int a, int b) {
        System.out.println("a = " + a + " b = " + b);
    }

    public void print() {
        System.out.println("hello world");
    }

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("com.github.zhuyaotong.demo.reflect.CreatingNewObjects");
            Class partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;

            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            Constructor ct = cls.getConstructor(partypes);
            Object retobj = ct.newInstance(arglist);
            ((CreatingNewObjects) retobj).print();
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}

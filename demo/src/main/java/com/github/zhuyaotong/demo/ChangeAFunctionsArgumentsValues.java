package com.github.zhuyaotong.demo;

public class ChangeAFunctionsArgumentsValues {
    public static void main(String[] args) {
        boolean in = false;
        truifier(in);
        System.out.println("in is " + in);
    }

    public static void truifier(boolean bool) {
        if (bool == false) {
            bool = true;
        }
        System.out.println("bool is " + bool);
    }
}

class Foo {
    boolean is = false;
}

class Test {

    static void trufier(Foo b) {
        b.is = true;
    }

    public static void main(String[] args) {
        // your code goes here
        Foo bar = new Foo();
        trufier(bar);
        System.out.println(bar.is); //Output: TRUE
    }
}


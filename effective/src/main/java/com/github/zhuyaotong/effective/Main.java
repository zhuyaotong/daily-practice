package com.github.zhuyaotong.effective;

public class Main {
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void main(String[] args) {
        boolean exist = Main.valueOf(true);
    }
}

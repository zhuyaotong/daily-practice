package com.github.zhuyaotong.p3c;

public class FinallyInsideReturn {
    private int x = 0;

    public int checkReturn() {
        try {
            // x 等于 1，此处不返回
            return ++x;
        } finally {
            // 返回的结果是 2
            return ++x;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FinallyInsideReturn().checkReturn());
    }
}

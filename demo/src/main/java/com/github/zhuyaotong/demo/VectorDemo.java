package com.github.zhuyaotong.demo;

import java.util.Random;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        Vector<Long> vector = new Vector<>();
        VectorDemo vd = new VectorDemo();
        vd.test(vector);
        vd.test(vector);
    }

    private void test(Vector<Long> vector) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                long l = new Random().nextLong();
                vector.add(l);
                vector.indexOf(l);
                vector.remove(vector.indexOf(l));
            }
        }).start();
    }
}

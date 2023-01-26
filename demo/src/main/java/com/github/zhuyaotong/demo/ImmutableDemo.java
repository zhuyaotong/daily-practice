package com.github.zhuyaotong.demo;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableDemo {
    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>();
        originalList.add("a");
        originalList.add("b");
        originalList.add("c");

        List<String> jdkUnmodifiableList = Collections.unmodifiableList(originalList);
        ImmutableList<String> guavaImmutableList = ImmutableList.copyOf(originalList);
//        jdkUnmodifiableList.add("d"); // 抛出UnsupportedOperationException
//        guavaImmutableList.add("d"); // 抛出UnsupportedOperationException
        originalList.add("d");

        print(originalList);
        print(jdkUnmodifiableList);
        print(guavaImmutableList);
    }

    private static void print(List<String> list) {
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}

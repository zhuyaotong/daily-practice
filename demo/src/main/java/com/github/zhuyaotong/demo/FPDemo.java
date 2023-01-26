package com.github.zhuyaotong.demo;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class FPDemo {
    public static void main(String[] args) {
        Optional<Integer> result = Stream.of("f", "ba", "hello")
                .map(String::length)
                .filter(l -> l <= 3)
                .max(Comparator.comparingInt(o -> o));
        System.out.println(result.get());
    }
}

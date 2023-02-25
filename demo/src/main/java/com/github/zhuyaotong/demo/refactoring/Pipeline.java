package com.github.zhuyaotong.demo.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Pipeline {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "orange", "banana");

        // method one 使用循环
        List<String> resultsOfLoop = getFilterOutput(fruits, "orange");
        for (String result : resultsOfLoop) {
            System.out.println(result);
        }

        System.out.println("=================");

        // method two 使用管道运算
        List<String> resultsOfPipeline = fruits.stream().filter(fruit -> !"orange".equals(fruit))
                .collect(Collectors.toList());
        resultsOfPipeline.forEach(System.out::println);
    }

    private static List<String> getFilterOutput(List<String> fruits, String filter) {
        List<String> result = new ArrayList<>();
        for (String fruit : fruits) {
            if (!filter.equals(fruit)) {
                result.add(fruit);
            }
        }
        return result;
    }
}

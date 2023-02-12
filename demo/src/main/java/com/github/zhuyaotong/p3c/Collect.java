package com.github.zhuyaotong.p3c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Collect {

    public static void main(String[] args) {
//        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
//        pairArrayList.add(new Pair<>("version", 12.10));
//        pairArrayList.add(new Pair<>("version", 12.19));
//        pairArrayList.add(new Pair<>("version", 6.28));
//        // 生成的 map 集合中只有一个键值对：{version=6.28}
//        Map<String, Double> map = pairArrayList.stream()
//                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
//        System.out.println(map);

//        String[] departments = new String[]{"RDC", "RDC", "KKB"};
//        // 抛出 IllegalStateException 异常
//        Map<Integer, String> map = Arrays.stream(departments)
//                .collect(Collectors.toMap(String::hashCode, str -> str));

        List<Pair<String, Double>> pairArrayList = new ArrayList<>(2);
        pairArrayList.add(new Pair<>("version1", 8.3));
        pairArrayList.add(new Pair<>("version2", null));
        // 抛出 NullPointerException 异常
        Map<String, Double> map = pairArrayList.stream()
//                .filter(p -> p.getKey() != null && p.getValue() != null)
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
        System.out.println(map);
    }

}

class Pair<K, V> {
    private final K k;
    private final V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return this.k;
    }

    public V getValue() {
        return this.v;
    }
}

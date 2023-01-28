package com.github.zhuyaotong.p3c;

import java.util.HashMap;
import java.util.Map;

// 反例
//public class ConfusingName {
//    protected int stock;
//    protected String alibaba;
//
//    // 非 setter/getter 的参数名称，不允许与本类成员变量同名
//    public void access(String alibaba) {
//        if (condition) {
//            final int money = 666;
//            // ...
//        }
//        for (int i = 0; i < 10; i++) {
//            // 在同一方法体中，不允许与其它代码块中的 money 命名相同
//            final int money = 15978;
//            // ...
//        }
//    }
//}

//class Son extends ConfusingName {
//    // 不允许与父类的成员变量名称相同
//    private int stock;
//}

class Constant {
    private static final Map<String, Object> cache = new HashMap<>();
    private static String tradeId;

    private static Object value;

    public static void main(String[] args) {
        // 开发者 A 定义了缓存的 key。
        String key = "Id#taobao_" + tradeId;
        cache.put(key, value);
        // 开发者 B 使用缓存时直接复制少了下划线，即 key 是"Id#taobao" + tradeId，导致出现故障。
//        String key = "Id#taobao" + tradeId;
        cache.get(key);
    }
}
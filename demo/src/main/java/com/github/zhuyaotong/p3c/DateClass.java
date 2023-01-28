package com.github.zhuyaotong.p3c;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateClass {
    public static void main(String[] args) {
        // 获取今年的天数
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        System.out.println(daysOfThisYear);
        // 获取指定某年的天数
        LocalDate.of(2011, 1, 1).lengthOfYear();

        // 第一种情况：在闰年 366 天时，出现数组越界异常
        int[] dayArray = new int[365];
        // 第二种情况：一年有效期的会员制，2020 年 1 月 26 日注册，硬编码 365 返回的却是 2021 年 1 月 25 日
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 26);
        calendar.add(Calendar.DATE, 365);

        Map<String, Object> map = new HashMap<>(16);
        if (map.isEmpty()) {
            System.out.println("no element in this map.");
        }
    }
}

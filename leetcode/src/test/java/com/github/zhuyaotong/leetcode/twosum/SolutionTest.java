package com.github.zhuyaotong.leetcode.twosum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    int[] nums = {2, 7, 11, 15};
    int target = 9;

    @Test
    @DisplayName("两数之和")
    void twoSum() {
        Solution solution = new Solution();
        assertEquals(Arrays.toString(new int[]{0, 1}), Arrays.toString(solution.twoSum(nums, target)), "结果应该为[0, 1]");
    }
}

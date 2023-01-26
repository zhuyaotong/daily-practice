package com.github.zhuyaotong.demo;

public class NumsDuplicate {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5};
        System.out.println(new NumsDuplicate().duplicate(nums));
    }

    public int duplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
            swap(nums, i, nums[i]);
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

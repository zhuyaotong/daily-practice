package com.github.zhuyaotong.leetcode.containerwithmostwater;

class Solution {

    public int maxArea(int[] height) {
        // [1,8,6,2,5,4,8,3,7]
        // [1, 1]
        int left = 0;
        int right = height.length - 1;
        int result = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution()
                        //.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})
                .maxArea(new int[] {1, 1}));
    }

}

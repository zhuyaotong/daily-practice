package com.github.zhuyaotong.leetcode.longestpalindromicsubstring;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = -1;
        StringBuilder t = new StringBuilder("#");

        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append("#");
        }
        t.append("#");
        s = t.toString();
        List<Integer> armLen = new ArrayList<>();
        int right = -1;
        int j = -1;

        for (int i = 0; i < s.length(); ++i) {
            int curArmLen;

            if (right >= i) {
                int iSym = j * 2 - i;
                int minArmLen = Math.min(armLen.get(iSym), right - i);
                curArmLen = expand(s, i - minArmLen, i + minArmLen);
            } else {
                curArmLen = expand(s, i, i);
            }

            armLen.add(curArmLen);

            if (i + curArmLen > right) {
                j = i;
                right = i + curArmLen;
            }

            if (curArmLen * 2 + 1 > end - start) {
                start = i - curArmLen;
                end = i + curArmLen;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        return (right - left - 2) / 2;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("cbbd"));
    }
}

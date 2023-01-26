package com.github.zhuyaotong.demo;

import org.junit.Assert;
import org.junit.Test;

public class NumsDuplicateTest {
    private int[] nums = {2, 3, 1, 0, 2, 5};

    @Test
    public void testDuplicate() {
        NumsDuplicate np = new NumsDuplicate();
        int result = np.duplicate(nums);
        Assert.assertEquals(2, result);
    }
}

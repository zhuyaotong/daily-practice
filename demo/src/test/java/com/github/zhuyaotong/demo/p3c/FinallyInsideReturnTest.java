package com.github.zhuyaotong.demo.p3c;

import com.github.zhuyaotong.p3c.FinallyInsideReturn;
import org.junit.Assert;
import org.junit.Test;

public class FinallyInsideReturnTest {

    @Test
    public void test_finally_inside_return() {
        Assert.assertEquals(2, new FinallyInsideReturn().checkReturn());
    }
}

package com.github.zhuyaotong.mybatisplus.service;

import com.github.zhuyaotong.mybatisplus.BaseTest;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import com.github.zhuyaotong.mybatisplus.entity.CouponRecord;
import com.github.zhuyaotong.mybatisplus.entity.QueryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CouponRecordServiceTest extends BaseTest {

    @Autowired
    private CouponRecordService couponRecordService;

    @Test
    void getReceivedCouponsByUid() {
        QueryEntity query = new QueryEntity();
        CouponRecord cr = new CouponRecord();
        cr.setUid(666L);
        cr.setUsedTag(0);
        query.setCr(cr);
        List<Coupon> result = couponRecordService.getReceivedCouponsByUid(query);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(result.get(0), result.get(1));

        cr.setUid(666L);
        cr.setUsedTag(1);
        query.setCr(cr);
        result = couponRecordService.getReceivedCouponsByUid(query);
        Assertions.assertEquals(0, result.size());
    }

}

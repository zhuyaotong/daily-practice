package com.github.zhuyaotong.mybatisplus.mapper;

import com.github.zhuyaotong.mybatisplus.BaseTest;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Slf4j
public class CouponMapperTest extends BaseTest {

    @Autowired
    private CouponMapper couponMapper;

    @Test
    public void getById() {
        Coupon result = couponMapper.selectById(1);
        Assertions.assertNotNull(result);
        log.info("{}", result);
    }

    @Test
    public void updateDeleteStatus() {
        int result = couponMapper.updateDeleteStatus(1, 1, new Date());
        Assertions.assertEquals(1, result);
        Coupon coupon = couponMapper.selectById(1);
        Assertions.assertEquals(1, coupon.getDeleteStatus());
        log.info("{}", coupon);

        result = couponMapper.updateDeleteStatus(1, 0, new Date());
        Assertions.assertEquals(1, result);
        coupon = couponMapper.selectById(1);
        Assertions.assertEquals(0, coupon.getDeleteStatus());
        log.info("{}", coupon);
    }

}

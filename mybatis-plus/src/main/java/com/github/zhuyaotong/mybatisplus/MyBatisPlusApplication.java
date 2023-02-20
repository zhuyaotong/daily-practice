package com.github.zhuyaotong.mybatisplus;

import com.github.zhuyaotong.mybatisplus.service.CouponRecordService;
import com.github.zhuyaotong.mybatisplus.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.zhuyaotong.mybatisplus.mapper")
@Slf4j
public class MyBatisPlusApplication implements CommandLineRunner {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRecordService couponRecordService;

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        log.info("id为1的优惠券：{}", couponService.getById(1));
//        log.info("id为666的用户领取的优惠券为：{}", couponRecordService.getReceivedCouponsByUid(666L));
    }
}

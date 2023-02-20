package com.github.zhuyaotong.mybatisplus.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MyCoupon {
    /**
     * 用户id
     */
    private Long uid;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 使用的标记：0代表未使用，1代表已使用
     */
    private Integer usedTag;

    /**
     * 过期时间
     */
    private LocalDateTime expiration;

    // 以上是 CouponRecord 的字段，以下是Coupon的

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 满减金额
     */
    private BigDecimal fullDiscountAmount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}

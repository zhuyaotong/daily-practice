package com.github.zhuyaotong.mybatisplus.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 优惠券
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
@Data
@EqualsAndHashCode
public class Coupon {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String name;

    /**
     * 每人领取的次数
     */
    private Integer receiptNumber;

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}

package com.github.zhuyaotong.mybatisplus.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
@Data
@EqualsAndHashCode
public class CouponRecord {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}

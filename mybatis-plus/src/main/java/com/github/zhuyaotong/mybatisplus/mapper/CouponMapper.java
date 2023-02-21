package com.github.zhuyaotong.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
public interface CouponMapper extends BaseMapper<Coupon> {
    int updateDeleteStatus(@Param("id") int id, @Param("deleteStatus") int deleteStatus, @Param("updateTime") Date updateTime);
}

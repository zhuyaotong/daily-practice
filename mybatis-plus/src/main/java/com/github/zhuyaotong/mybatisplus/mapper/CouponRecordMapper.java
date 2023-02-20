package com.github.zhuyaotong.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import com.github.zhuyaotong.mybatisplus.entity.CouponRecord;
import com.github.zhuyaotong.mybatisplus.entity.QueryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
public interface CouponRecordMapper extends BaseMapper<CouponRecord> {

    /**
     * 根据用户id获取领取的优惠券
     *
     * @param uid
     * @return
     */
    List<Coupon> getReceivedCouponsByUid(@Param("query") QueryEntity query);
}

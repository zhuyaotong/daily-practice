package com.github.zhuyaotong.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import com.github.zhuyaotong.mybatisplus.entity.CouponRecord;
import com.github.zhuyaotong.mybatisplus.entity.QueryEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
public interface CouponRecordService extends IService<CouponRecord> {
    /**
     * 获取领取的优惠券
     *
     * @param uid
     * @return
     */
    List<Coupon> getReceivedCouponsByUid(QueryEntity query);
}

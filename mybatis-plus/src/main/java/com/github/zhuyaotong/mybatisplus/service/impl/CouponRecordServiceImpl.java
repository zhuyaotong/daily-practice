package com.github.zhuyaotong.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zhuyaotong.mybatisplus.entity.Coupon;
import com.github.zhuyaotong.mybatisplus.entity.CouponRecord;
import com.github.zhuyaotong.mybatisplus.entity.QueryEntity;
import com.github.zhuyaotong.mybatisplus.mapper.CouponRecordMapper;
import com.github.zhuyaotong.mybatisplus.service.CouponRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhuyaotong
 * @since 2023-02-19
 */
@Service
public class CouponRecordServiceImpl extends ServiceImpl<CouponRecordMapper, CouponRecord> implements CouponRecordService {

    @Override
    public List<Coupon> getReceivedCouponsByUid(QueryEntity query) {
        return this.baseMapper.getReceivedCouponsByUid(query);
    }

}

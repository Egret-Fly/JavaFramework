package com.xd.eduOrder.service;

import com.xd.eduOrder.domain.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-12-23
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);
}

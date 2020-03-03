package com.ibt.osess.Service;

import com.ibt.osess.Domain.Order;
import com.ibt.osess.Domain.Suborder;
import com.ibt.osess.Domain.WebResult;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Service
 * @Author: keer
 * @CreateTime: 2020-03-03 20:49
 * @Description: 订单接口
 */
public interface OrderService {
    /**
     * 产生新订单的接口
     *
     * @param order
     * @return
     */
    WebResult makeOrder(Order order, String key);

    /**
     * 产生子订单接口
     *
     * @return
     */
    WebResult makeSubOrder(Suborder suborder, String key);
}

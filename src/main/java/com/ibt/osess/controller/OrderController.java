package com.ibt.osess.controller;

import com.ibt.osess.Domain.Order;
import com.ibt.osess.Domain.Suborder;
import com.ibt.osess.Domain.WebResult;
import com.ibt.osess.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.controller
 * @Author: keer
 * @CreateTime: 2020-03-02 20:19
 * @Description: 订单接口
 */
@RestController
public class OrderController {
    protected static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    /**
     * 生成订单
     *
     * @param order
     */
    @PostMapping("/makeOrder")
    public WebResult makeOrder(@RequestBody Order order, @RequestParam Map map) {
        WebResult webResult = new WebResult();
        String key = null;

        if (map.containsKey("key")) {
            key = (String) map.get("key");
        } else {
            webResult.setMessage("缺少密钥信息，登录信息是否正确");
            webResult.setStatus(WebResult.ERROR);
            logger.warn("请求错误：缺少密钥信息，登录信息是否正确");
            return webResult;
        }
        return orderService.makeOrder(order, key);
    }

    @PostMapping("/makeSuborder")
    public WebResult makeSuborder(@RequestBody Suborder suborder, @RequestParam Map map) {
        WebResult webResult = new WebResult();
        String key = null;

        if (map.containsKey("key")) {
            key = (String) map.get("key");
        } else {
            webResult.setMessage("缺少密钥信息，登录信息是否正确");
            webResult.setStatus(WebResult.ERROR);
            logger.warn("请求错误：缺少密钥信息，登录信息是否正确");
            return webResult;
        }


        return orderService.makeSubOrder(suborder,key);
    }

    //TODO 目前controller层的log打印规则还没定好，先不打印
    //TODO 现在获取密钥的方式是将密钥字符串放在了请求的字段中，这种方法不太安全，在联调时应改为cookie存储密钥字符串
}

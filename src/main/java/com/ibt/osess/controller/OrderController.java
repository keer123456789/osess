package com.ibt.osess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    /**
     * 生成订单
     * @param map
     */
    @PostMapping("/makeOrder")
    public void makeOrder(@RequestBody Map map){

    }
}

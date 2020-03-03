package com.ibt.osess.Service.implement;

import com.ibt.osess.BigchainDB.BigchainDBUtil;
import com.ibt.osess.Domain.Order;
import com.ibt.osess.Domain.WebResult;
import com.ibt.osess.Service.OrderService;
import com.ibt.osess.controller.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Service.implement
 * @Author: keer
 * @CreateTime: 2020-03-03 20:52
 * @Description: 订单接口实现类
 */
@Service
public class OrderServiceImp implements OrderService {
    protected static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

    /**
     * 创建订单，将订单信息传送到BigchainDB上
     * @param order 订单信息
     * @param key 用户密钥
     * @return
     */
    @Override
    public WebResult makeOrder(Order order,String key) {
        WebResult webResult=new WebResult();
        BigchainDBUtil dbUtil=new BigchainDBUtil();
        String id = dbUtil.createAsset(order,key);
        if(!id.equals(null)){
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setData(id);
            webResult.setMessage("makeOrder success");
        }else{
            webResult.setStatus(WebResult.ERROR);
            webResult.setMessage("makeOrder fail");
        }
        return webResult;

    }


}

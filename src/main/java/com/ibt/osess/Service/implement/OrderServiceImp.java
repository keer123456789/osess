package com.ibt.osess.Service.implement;

import com.bigchaindb.model.Asset;
import com.ibt.osess.BigchainDB.BigchainDBUtil;
import com.ibt.osess.Domain.Order;
import com.ibt.osess.Domain.Suborder;
import com.ibt.osess.Domain.WebResult;
import com.ibt.osess.Service.OrderService;
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
     *
     * @param order 订单信息
     * @param key   用户密钥
     * @return
     */
    @Override
    public WebResult makeOrder(Order order, String key) {
        WebResult<String> webResult = new WebResult<>();
        BigchainDBUtil dbUtil = new BigchainDBUtil();
        String id = dbUtil.createAsset(order, key);
        if (id != null) {
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setData(id);
            webResult.setMessage("Order build success");
            logger.info("创建订单成功！");
        } else {
            webResult.setStatus(WebResult.ERROR);
            webResult.setMessage("Order build fail");
            logger.error("******创建订单失败******");
        }
        return webResult;

    }

    @Override
    public WebResult makeSubOrder(Suborder suborder, String key) {
        WebResult<String> webResult = new WebResult<>();
        BigchainDBUtil dbUtil = new BigchainDBUtil();
        //使用订单号查询资产
        Asset asset = dbUtil.selectAssetBySearchKey(suborder.getOrderID());
        Order order;
        if (asset != null) {
            order = (Order) asset.getData();
            //TODO : 将asset转化为对象类，linkedTreeMap 转化 对象类
            //如果该订单号的资产存在，检查订单中的用户ID和子订单中的用户ID是否相同
            if (!order.getUserID().equals(suborder.getUserID())) {
                logger.error("******子订单信息错误******");
                webResult.setStatus(WebResult.ERROR);
                webResult.setMessage("suborder info error");
                return webResult;
            }
        } else {
            logger.error("******子订单信息错误******");
            webResult.setStatus(WebResult.ERROR);
            webResult.setMessage("suborder info error");
            return webResult;
        }


        String TXID=dbUtil.transferToSelf(asset.getId(),suborder,key);
        if (TXID != null) {
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setData(TXID);
            webResult.setMessage("suborder build success");
            logger.info("创建子订单成功！");
        } else {
            webResult.setStatus(WebResult.ERROR);
            webResult.setMessage("suborder build fail");
            logger.error("******创建子订单失败******");
        }
        return webResult;
    }
}

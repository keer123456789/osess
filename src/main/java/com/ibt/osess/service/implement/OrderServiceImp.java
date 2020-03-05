package com.ibt.osess.service.implement;

import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibt.osess.bigchaindb.BigChainDbUtil;
import com.ibt.osess.pojo.Order;
import com.ibt.osess.pojo.Suborder;
import com.ibt.osess.pojo.WebResult;
import com.ibt.osess.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

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

        /**
         * 添加订单信息
         */
        BigChainDbUtil dbUtil = new BigChainDbUtil();
        String id = dbUtil.createAsset(order, key);

        /**
         * 检查订单信息是否添加成功
         */
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

    /**
     * 为订单添加子订单
     *
     * @param suborder 子订单
     * @param key      用户密钥
     * @return
     */
    @Override
    public WebResult makeSubOrder(Suborder suborder, String key) {
        WebResult<String> webResult = new WebResult<>();

        /**
         * 检查子订单信息
         */
        if (!checkOrderScoreInfo(suborder)) {
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setMessage("suborder info error");
            return webResult;
        }

        /**
         * 查找对应订单号的资产
         */
        BigChainDbUtil dbUtil = new BigChainDbUtil();
        Asset asset = dbUtil.selectAssetBySearchKey(suborder.getOrderID()).getAssets().get(0);

        /**
         * 给订单资产添加子订单
         */
        String TXID = dbUtil.transferToSelf(asset.getId(), suborder, key);

        /**
         * 检查子订单添加是否成功
         */
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


    /**
     * 检查子订单信息是否正确
     *
     * @param suborder
     * @return
     */
    private boolean checkOrderScoreInfo(Suborder suborder) {
        BigChainDbUtil dbUtil = new BigChainDbUtil();
        /**
         * 使用订单号查询对应资产是否存在
         */
        Assets assets = dbUtil.selectAssetBySearchKey(suborder.getOrderID());
        if (assets.size() != 1) {
            logger.error("******订单号错误！******");
            return false;
        }
        Asset asset = assets.getAssets().get(0);
        if (asset == null) {
            logger.error("******子订单信息错误******");
            return false;
        }

        /**
         * 将asset.data 转化为 Order对象
         */
        Gson gson = new Gson();
        String json = gson.toJson(asset.getData());
        Type type = new TypeToken<Order>() {
        }.getType();
        Order order = gson.fromJson(json, type);

        /**
         * 检查订单中的用户ID和子订单中的用户ID是否相同
         */
        if (!order.getUserID().equals(suborder.getUserID())) {
            logger.error("******子订单信息错误******");
            return false;
        }

        return true;
    }
}

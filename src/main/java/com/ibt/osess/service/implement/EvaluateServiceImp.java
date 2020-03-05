package com.ibt.osess.service.implement;

import com.bigchaindb.model.Asset;
import com.bigchaindb.model.Assets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibt.osess.bigchaindb.BigChainDbUtil;
import com.ibt.osess.pojo.*;
import com.ibt.osess.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.service.implement
 * @Author: keer
 * @CreateTime: 2020-03-04 22:25
 * @Description: 评价服务接口实现
 */
@Service
public class EvaluateServiceImp implements EvaluateService {
    protected Logger logger = LoggerFactory.getLogger(EvaluateServiceImp.class);

    /**
     * 给子订单添加评分成功
     *
     * @param score 评分信息
     * @param key   用户密钥
     * @return
     */
    @Override
    public WebResult buildScore(SuborderScore score, String key) {
        WebResult webResult = new WebResult();

        /**
         * 检查子订单评分信息是否正确
         */
        if (!checkSuborderScoreInfo(score)) {
            webResult.setMessage("suborder score info error");
            webResult.setStatus(WebResult.ERROR);
            return webResult;
        }

        /**
         * 查找对应的订单号资产ID
         */
        BigChainDbUtil dbUtil = new BigChainDbUtil();
        Asset asset = dbUtil.selectAssetBySearchKey(score.getOrderID()).getAssets().get(0);

        /**
         * 添加评价信息
         */
        String TXID = dbUtil.transferToSelf(asset.getId(), score, key);

        /**
         * 检查评价信息是否添加成功
         */
        if (TXID != null) {
            webResult.setStatus(WebResult.SUCCESS);
            webResult.setData(TXID);
            webResult.setMessage("add suborder score  success");
            logger.info("添加子订单评分成功！");
        } else {
            webResult.setStatus(WebResult.ERROR);
            webResult.setMessage("add suborder score fail");
            logger.error("******添加子订单评分失败******");
        }
        return webResult;
    }

    /**
     * 检查评价信息的正确性
     * 先检查 订单号存在，和用户是否对应
     * 再检查 子订单信息：订单号，交通方式，交通工具号，供应商信息
     *
     * @param score 评价信息
     * @return 正确返回 true，错误返回 false
     */
    private boolean checkSuborderScoreInfo(SuborderScore score) {
        BigChainDbUtil dbUtil = new BigChainDbUtil();

        Assets assets = dbUtil.selectAssetBySearchKey(score.getOrderID());
        if (assets.size() != 1) {
            logger.error("******订单号错误！******");
            return false;
        }
        Asset asset = assets.getAssets().get(0);
        //如果该订单号的资产存在
        if (asset == null) {
            logger.error("******评价信息错误******");
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
         * 检查订单中的用户ID和评价中的用户ID是否相同
         */
        if (!order.getUserID().equals(score.getUserID())) {
            logger.error("******评价信息错误******");
            return false;
        }

        List<MetaData> list = dbUtil.selectMetaDataByAssetID(asset.getId());
        int count = 0;
        for (MetaData metaData : list) {
            json = gson.toJson(metaData.getMetadata());
            type = new TypeToken<Suborder>() {
            }.getType();
            Suborder suborder = gson.fromJson(json, type);

            /**
             * 检查子订单的信息和评价信息是否对应
             */
            boolean isExit = suborder.getSuborderID().equals(score.getSuborderID())
                    && suborder.getTrafficTool().equals(score.getTrafficTool())
                    && suborder.getTrafficToolID().equals(score.getTrafficToolID())
                    && suborder.getTrafficName().equals(score.getTrafficName());
            if (isExit) {
                count++;
            }
        }
        if (count == 0) {
            logger.error("******评价信息错误******");
            return false;
        }
        return true;
    }
}

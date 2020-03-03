package com.ibt.osess.BigchainDB;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Transaction;
import com.ibt.osess.Service.implement.OrderServiceImp;
import com.sun.org.apache.bcel.internal.generic.IXOR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.BigchainDB
 * @Author: keer
 * @CreateTime: 2020-03-03 20:22
 * @Description: bigchaindb Driver工具类
 */
public class BigchainDBUtil {
    protected static final Logger logger = LoggerFactory.getLogger(BigchainDBUtil.class);

    /**
     * 创建资产，只有asset数据，没有metadata数据
     *
     * @param assetData asset数据
     * @param key       密钥字符串
     * @return
     * @throws Exception
     */
    public String createAsset(Object assetData, String key) {
        return createAsset(assetData, null, key);
    }

    /**
     * 创建资产，既有asset数据，也有metadata数据
     *
     * @param assetData asset数据
     * @param metadata  metadata数据
     * @param key       密钥字符串
     * @return
     * @throws Exception
     */
    public String createAsset(Object assetData, Object metadata, String key) {

        Transaction createTransaction = null;
        try {
            createTransaction = BigchainDbTransactionBuilder
                    .init()
                    .operation(Operations.CREATE)
                    .addAssets(assetData, assetData.getClass())
                    .addMetaData(metadata)
                    .buildAndSign(
                            KeyPairHolder.getPublic(key),
                            KeyPairHolder.getPrivate(key))
                    .sendTransaction();
        } catch (Exception e) {
            logger.warn("***创建交易时签名失败***");
            e.printStackTrace();

        }

        String TXID = createTransaction.getId();
        if (!checkTransactionExit(TXID)) {
            logger.error("******创建资产交易失败******");
            return null;
        }
        logger.info("创建资产交易成功！");
        return createTransaction.getId();
    }


    /**
     * 通过交易id查询交易是否存在
     *
     * @param txID 交易ID
     * @return
     */
    public boolean checkTransactionExit(String txID) {
        try {
            Transaction transaction = TransactionsApi.getTransactionById(txID);
            if (transaction.getId().equals(txID)) {
                logger.info("交易存在！！ID：" + txID);
                return true;
            } else {
                logger.info("交易不存在！！ID：" + txID);
                return false;
            }
        } catch (Exception e) {
            logger.error("未知错误！！！");
            e.printStackTrace();
            return false;
        }
    }


}

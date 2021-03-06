package com.ibt.osess.bigchaindb;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibt.osess.pojo.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.BigchainDB
 * @Author: keer
 * @CreateTime: 2020-03-03 20:22
 * @Description: bigchaindb Driver工具类
 */
public class BigChainDbUtil {
    protected static final Logger logger = LoggerFactory.getLogger(BigChainDbUtil.class);

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
            logger.warn("***创建交易失败***");
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
     * 给资产增加metadata数据，不是交易资产
     *
     * @param assetID  资产id
     * @param metadata metadata数据
     * @param key      密钥字符串
     * @return
     */
    public String transferToSelf(String assetID, Object metadata, String key) {
        Transaction transaction = null;
        try {
            transaction = BigchainDbTransactionBuilder
                    .init()
                    .operation(Operations.TRANSFER)
                    .addAssets(assetID, String.class)
                    .addMetaData(metadata)
                    .addInput(null, findLastOutput(assetID), KeyPairHolder.getPublic(key))
                    .addOutput("1", KeyPairHolder.getPublic(key))
                    .buildAndSign(
                            KeyPairHolder.getPublic(key),
                            KeyPairHolder.getPrivate(key))
                    .sendTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("******给资产增加metadata数据失败******");
            return null;
        }

        //检查交易是否成功上链
        String TXID = transaction.getId();
        if (!checkTransactionExit(TXID)) {
            logger.error("******给资产增加metadata数据失败******");
            return null;
        }

        return TXID;
    }

    /**
     * 根据资产ID查找关于该笔资产的最后一笔交易输出
     *
     * @param assetID
     * @return
     */
    private FulFill findLastOutput(String assetID) {
        FulFill fulFill = new FulFill();
        Transactions transactions = null;
        try {
            transactions = TransactionsApi.getTransactionsByAssetId(assetID, Operations.TRANSFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Transaction> list = transactions.getTransactions();
        if (list != null && list.size() > 0) {

            /**
             * 这里是修改交易id，直接用 list.get(list.size() - 1).getId() 获得的交易ID格式不对
             * 例：""234abced342313455676767""
             * 会在首尾多出一个"",只会让 构造出来的输入出错，最终BigchainDB会报错：404，找不到这笔交易
             * 所以在这做一下处理
             */
            String id = list.get(list.size() - 1).getId();
            id = id.substring(1, id.length() - 1);

            fulFill.setTransactionId(id);
        } else {
            fulFill.setTransactionId(assetID);
        }
        return fulFill;
    }


    /**
     * 通过交易id查询交易是否存在
     *
     * @param txID 交易ID
     * @return
     */
    public boolean checkTransactionExit(String txID) {
        //TODO:目前在创建完交易，立即使用TXID查询不能及时查到，原因是数据上链需要一定时间，目前做法是程序暂停2秒。这个问题待解决

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    /**
     * 根据关键字查询资产
     *
     * @param searchKey
     * @return
     */
    public Assets selectAssetBySearchKey(String searchKey) {
        try {
            return AssetsApi.getAssets(searchKey);
        } catch (IOException e) {
            logger.warn("***资产关键字查询失败，关键字：" + searchKey + "***");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过资产ID寻找metadata数据
     *
     * @param AssetID
     * @return
     */
    public List<MetaData> selectMetaDataByAssetID(String AssetID) {
        Transactions transactions = null;
        try {
            transactions = TransactionsApi.getTransactionsByAssetId(AssetID, Operations.TRANSFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<MetaData> metaDataList = new ArrayList<>();
        for (Transaction transaction : transactions.getTransactions()) {
            Map map = (Map) transaction.getMetaData();
            MetaData metaData = new MetaData();
            metaData.setId(transaction.getId());
            metaData.setMetadata(map);
            metaDataList.add(metaData);
        }
        return metaDataList;

    }


}

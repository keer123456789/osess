package com.ibt.osess.BigchainDB;


import com.bigchaindb.util.KeyPairUtils;
import com.ibt.osess.controller.OrderController;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.security.KeyPair;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.BigchainDB
 * @Author: keer
 * @CreateTime: 2020-03-03 20:23
 * @Description: 密钥管理类
 */
public class KeyPairHolder {
    protected static final Logger logger = LoggerFactory.getLogger(KeyPairHolder.class);

    /**
     * 生成BigchainDB密钥，以字符串的形式返回
     * @return 返回私钥
     */
    public String makeKeyPair(){
        KeyPairGenerator keyPairGenerator=new KeyPairGenerator() ;
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        return KeyPairUtils.encodePrivateKeyBase64(keyPair);

    }

    /**
     * 通过密钥字符串得到公钥
     * @param key
     * @return
     */
    public static EdDSAPublicKey getPublic(String key){
        return (EdDSAPublicKey)KeyPairUtils.decodeKeyPair(key).getPublic();
    }

    /**
     * 通过密钥字符串得到私钥
     * @param key
     * @return
     */
    public static EdDSAPrivateKey getPrivate(String key){
        return (EdDSAPrivateKey) KeyPairUtils.decodeKeyPair(key).getPrivate();
    }

    /**
     * 调试方法，项目完成后删除此方法
     * @return
     */
    public String getKeyPairFromTXT(){
        try {
            FileInputStream in = new FileInputStream("./keypair.txt");
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            String key = new String(buffer);
            logger.info("成功获得./keypair.txt 路径下的密钥");
            return key;
        } catch (Exception e) {
            logger.error("./keypair.txt 路径下没有密钥文件");
            return null;
        }
    }
}

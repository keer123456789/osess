package com.ibt.osess.init;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.init
 * @Author: keer
 * @CreateTime: 2020-03-02 20:10
 * @Description: BigchainDB启动类，项目启动后自动连接配置文件中BigchainDB节点地址
 */
@Component
public class BigChainDbSetupRunner implements CommandLineRunner {
    @Value("${BigchainDB_URL}")
    private String url;

    /**
     * 连接BigchainDB节点
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        BigchainDbConfigBuilder.baseUrl(url).setup();
    }
}

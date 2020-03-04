package com.ibt.osess.service.implement;

import com.ibt.osess.bigchaindb.BigChainDBUtil;
import com.ibt.osess.pojo.SuborderScore;
import com.ibt.osess.pojo.WebResult;
import com.ibt.osess.service.EvaluateService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.service.implement
 * @Author: keer
 * @CreateTime: 2020-03-04 22:25
 * @Description: 评价服务接口实现
 */
@Service
public class EvaluateServiceImp implements EvaluateService {
    @Override
    public WebResult buildScore(SuborderScore score) {
        WebResult webResult=new WebResult();

        BigChainDBUtil dbUtil=new BigChainDBUtil();

        return null;
    }
}

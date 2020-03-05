package com.ibt.osess.service;

import com.ibt.osess.pojo.SuborderScore;
import com.ibt.osess.pojo.WebResult;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.service
 * @Author: keer
 * @CreateTime: 2020-03-04 22:22
 * @Description: 评价服务接口
 */
public interface EvaluateService {
    /**
     * 给子订单评分
     * @param score
     * @param key
     * @return
     */
    WebResult buildScore(SuborderScore score,String key);
}

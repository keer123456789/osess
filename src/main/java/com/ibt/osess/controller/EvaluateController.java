package com.ibt.osess.controller;

import com.ibt.osess.pojo.SuborderScore;
import com.ibt.osess.pojo.WebResult;
import com.ibt.osess.service.EvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.controller
 * @Author: keer
 * @CreateTime: 2020-03-02 20:36
 * @Description: 交通方式评价接口
 */
@RestController
public class EvaluateController {
    protected static final Logger logger = LoggerFactory.getLogger(EvaluateController.class);

    @Autowired
    EvaluateService evaluateService;
    /**
     * 给子订单评分
     * @param score
     * @return
     */
    @PostMapping("/score")
    public WebResult buildScore(@RequestBody SuborderScore score, @RequestParam Map map) {

        String key = null;
        if (map.containsKey("key")) {
            key = (String) map.get("key");
        } else {
            WebResult webResult = new WebResult();
            webResult.setMessage("缺少密钥信息，登录信息是否正确");
            webResult.setStatus(WebResult.ERROR);
            logger.warn("请求错误：缺少密钥信息，登录信息是否正确");
            return webResult;
        }
        return evaluateService.buildScore(score,key);
    }


}

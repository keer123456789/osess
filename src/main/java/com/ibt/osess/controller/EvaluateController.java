package com.ibt.osess.controller;

import com.ibt.osess.pojo.SuborderScore;
import com.ibt.osess.pojo.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 给子订单评分
     * @param score
     * @return
     */
    @PostMapping("/buildScore")
    public WebResult buildScore(@RequestBody SuborderScore score) {
        WebResult webResult = new WebResult();

        return webResult;
    }


}

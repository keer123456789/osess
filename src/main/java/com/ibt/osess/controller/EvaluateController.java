package com.ibt.osess.controller;

import com.ibt.osess.Domain.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.windows.WEmbeddedFrame;

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
     * 公交车评价和指标评分
     *
     * @param map
     * @return
     */
    @PostMapping("/busscore")
    public WebResult busScore(@RequestBody Map map) {
        WebResult webResult = new WebResult();

        return webResult;
    }

    /**
     * 地铁各指标评分
     *
     * @param map
     * @return
     */
    @PostMapping("/subwayscore")
    public WebResult subwayScore(@RequestBody Map map) {
        WebResult webResult = new WebResult();

        return webResult;
    }

    /**
     * 出租车各指标评分
     * @param map
     * @return
     */
    @PostMapping("/taxiscore")
    public WebResult taxiScore(@RequestBody Map map) {
        WebResult webResult = new WebResult();

        return webResult;
    }


    /**
     * 共享单车各指标评分
     * @param map
     * @return
     */
    @PostMapping("/bikescore")
    public WebResult bikeScore(@RequestBody Map map){
        WebResult webResult = new WebResult();

        return webResult;
    }
}

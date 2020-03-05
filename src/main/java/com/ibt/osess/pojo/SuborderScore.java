package com.ibt.osess.pojo;

import java.util.Map;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.pojo
 * @Author: keer
 * @CreateTime: 2020-03-04 22:03
 * @Description: 子订单评分
 */
public class SuborderScore {
    /**
     * 交通方式
     */
    String trafficTool;
    /**
     * 交通工具ID
     */
    String trafficToolID;
    /**
     * 供应商名称
     */
    String trafficName;
    /**
     * 子订单号
     */
    String suborderID;
    /**
     * 订单号
     */
    String orderID;
    /**
     * 用户ID
     */
    String userID;
    /**
     * 评分
     */
    Map<String, StandardItem> standards;

    public SuborderScore() {
    }

    public String getTrafficTool() {
        return trafficTool;
    }

    public String getTrafficToolID() {
        return trafficToolID;
    }

    public String getTrafficName() {
        return trafficName;
    }

    public String getSuborderID() {
        return suborderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public Map<String, StandardItem> getStandards() {
        return standards;
    }

    public void setTrafficTool(String trafficTool) {
        this.trafficTool = trafficTool;
    }

    public void setTrafficToolID(String trafficToolID) {
        this.trafficToolID = trafficToolID;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
    }

    public void setSuborderID(String suborderID) {
        this.suborderID = suborderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setStandards(Map<String, StandardItem> standards) {
        this.standards = standards;
    }

    @Override
    public String toString() {
        return "SuborderScore{" +
                "trafficTool='" + trafficTool + '\'' +
                ", trafficToolID='" + trafficToolID + '\'' +
                ", trafficName='" + trafficName + '\'' +
                ", suborderID='" + suborderID + '\'' +
                ", orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", standards=" + standards +
                '}';
    }
}

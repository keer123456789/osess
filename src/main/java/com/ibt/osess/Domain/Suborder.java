package com.ibt.osess.Domain;

import java.util.Map;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Domain
 * @Author: keer
 * @CreateTime: 2020-03-03 22:18
 * @Description: 子订单对象
 */
public class Suborder {
    private String suborderID; //子订单ID
    private String orderID; //主订单ID
    private String userID; //评价该订单的乘客ID
    private String subStart; // 子订单起点
    private String subEnd; // 子订单终点
    private Map<String, Object> way; //交通方式
    private String subStartTime; //出发时间
    private String subEndTime; //终点时间

    public Suborder() {
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

    public String getSubStart() {
        return subStart;
    }

    public String getSubEnd() {
        return subEnd;
    }

    public Map<String, Object> getWay() {
        return way;
    }

    public String getSubStartTime() {
        return subStartTime;
    }

    public String getSubEndTime() {
        return subEndTime;
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

    public void setSubStart(String subStart) {
        this.subStart = subStart;
    }

    public void setSubEnd(String subEnd) {
        this.subEnd = subEnd;
    }

    public void setWay(Map<String, Object> way) {
        this.way = way;
    }

    public void setSubStartTime(String subStartTime) {
        this.subStartTime = subStartTime;
    }

    public void setSubEndTime(String subEndTime) {
        this.subEndTime = subEndTime;
    }

    @Override
    public String toString() {
        return "Suborder{" +
                "suborderID='" + suborderID + '\'' +
                ", orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", subStart='" + subStart + '\'' +
                ", subEnd='" + subEnd + '\'' +
                ", way=" + way +
                ", subStartTime='" + subStartTime + '\'' +
                ", subEndTime='" + subEndTime + '\'' +
                '}';
    }
}

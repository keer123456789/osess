package com.ibt.osess.pojo;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Domain
 * @Author: keer
 * @CreateTime: 2020-03-03 22:18
 * @Description: 子订单对象
 */
public class Suborder {
    /**
     * 子订单ID
     */
    private String suborderID;
    /**
     * 主订单ID
     */
    private String orderID;
    /**
     * 评价该订单的乘客ID
     */
    private String userID;
    /**
     * 子订单起点
     */
    private String subStart;
    /**
     * 子订单终点
     */
    private String subEnd;
    /**
     * 交通工具类型
     */
    private String trafficTool;
    /**
     * 交通工具ID
     */
    private String trafficID;
    /**
     * 交通工具供应商名称
     */
    private String trafficName;
    /**
     * 出发时间
     */
    private String subStartTime;
    /**
     * 终点时间
     */
    private String subEndTime;

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

    public String getTrafficTool() {
        return trafficTool;
    }

    public String getTrafficID() {
        return trafficID;
    }

    public String getTrafficName() {
        return trafficName;
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

    public void setTrafficTool(String trafficTool) {
        this.trafficTool = trafficTool;
    }

    public void setTrafficID(String trafficID) {
        this.trafficID = trafficID;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
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
                ", trafficTool='" + trafficTool + '\'' +
                ", trafficID='" + trafficID + '\'' +
                ", trafficName='" + trafficName + '\'' +
                ", subStartTime='" + subStartTime + '\'' +
                ", subEndTime='" + subEndTime + '\'' +
                '}';
    }
}

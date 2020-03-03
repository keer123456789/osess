package com.ibt.osess.Domain;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Domain
 * @Author: keer
 * @CreateTime: 2020-03-03 20:02
 * @Description: 订单对象
 */
public class Order {
    private String userID; //用户ID
    private String start;  //起点
    private String end;  //终点
    private String orderID; // 订单号
    private String startTime; //开始时间
    private String endTime; //结束时间

    public Order() {
    }

    public String getUserID() {
        return userID;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userID='" + userID + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", orderID='" + orderID + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

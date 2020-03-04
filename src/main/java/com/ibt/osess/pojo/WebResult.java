package com.ibt.osess.pojo;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.Domain
 * @Author: keer
 * @CreateTime: 2020-03-02 20:27
 * @Description: 接口返回值
 * status代表请求成功与否：0为成功，1为失败
 * data 代表返回的数据
 * message 代表请求的返回值说明信息
 */
public class WebResult<T> {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    private int status;
    private T data;
    private String message;

    public WebResult() {
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

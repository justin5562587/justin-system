package com.justin.system.entity.basic;

public class ResponseWrapper {

    private String status;
    private Integer code;
    private String message;
    private Object data;

    public ResponseWrapper() {
    }

    public ResponseWrapper(String status, Integer code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseWrapper success(Object message) {
        return new ResponseWrapper("success", 200, "successInfo", message);
    }

    public static ResponseWrapper fail(Object message) {
        return new ResponseWrapper("fail", 500, "failInfo", message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum ResponseStatus {
        SUCCESS, FAIL;
    }

}

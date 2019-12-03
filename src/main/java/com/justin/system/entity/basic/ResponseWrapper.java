package com.justin.system.entity.basic;

public class ResponseWrapper {

    private String status;
    private Integer code;
    private String message;
    private Object data;

    public ResponseWrapper() {
        this.data = null;
    }

    public ResponseWrapper(String status, Integer code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseWrapper success(Object data) {
        return new ResponseWrapper(ResponseStatus.SUCCESS.getMessage(), 200, "customer_200_success", data);
    }

    public static ResponseWrapper fail(Object data) {
        return new ResponseWrapper(ResponseStatus.FAIL.getMessage(), 400, "customer_400_fail", data);
    }

    public static ResponseWrapper error(Object data) {
        return new ResponseWrapper(ResponseStatus.ERROR.getMessage(), 500, "customer_500_error", data);
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
        SUCCESS("success"), FAIL("fail"), ERROR("error");

        private String message;

        ResponseStatus(String message) {
            this.message = message;
        }

        String getMessage() {
            return message;
        }
    }

}

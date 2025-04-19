package com.yaya.submitt.pojo;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    private String message;
    private Integer code;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public ResponseMessage(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
    //接口请求成功，调用这个接口
    public static <T> ResponseMessage <T> success(T data) {
        //System.out.println(data);
        return new ResponseMessage<>("success", HttpStatus.OK.value(), data);
    }

    public static <T> ResponseMessage <T> success() {
        //System.out.println(data);
        return new ResponseMessage<>("success", HttpStatus.OK.value(),null);
    }

    public static <T> ResponseMessage <T> error(String message) {
        //System.out.println(data);
        return new ResponseMessage<>(message, HttpStatus.OK.value(),null);
    }
}


package com.security.springSecurity.errorBean;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


public class CustomException extends RuntimeException {
    HttpStatus status;
    String msg;

    public CustomException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public CustomException() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}

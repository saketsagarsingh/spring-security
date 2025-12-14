package com.security.springSecurity.errorBean;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ErrorResponse {
    HttpStatus status;
    String msg;
    Long timeMilis;

    public ErrorResponse(HttpStatus status, String msg, Long timeMilis) {
        this.status = status;
        this.msg = msg;
        this.timeMilis = timeMilis;
    }

    public ErrorResponse(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ErrorResponse() {
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

    public Long getTimeMilis() {
        return timeMilis;
    }

    public void setTimeMilis(Long timeMilis) {
        this.timeMilis = timeMilis;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", timeMilis=" + timeMilis +
                '}';
    }
}

package com.mferreira.coopapi.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private ErrorMessage msg;
    private HttpStatus status;

    public BusinessException(ErrorMessage msg) {
        this.msg = msg;
        this.status = msg.getStatus();
    }

    public ErrorMessage getMsg() {
        return msg;
    }

    public void setMsg(ErrorMessage msg) {
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

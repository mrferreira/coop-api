package com.mferreira.coopapi.exception;

public class BusinessException extends RuntimeException {
    private ErrorMessage msg;

    public BusinessException(ErrorMessage msg) {
        this.msg = msg;
    }

    public ErrorMessage getMsg() {
        return msg;
    }

    public void setMsg(ErrorMessage msg) {
        this.msg = msg;
    }
}

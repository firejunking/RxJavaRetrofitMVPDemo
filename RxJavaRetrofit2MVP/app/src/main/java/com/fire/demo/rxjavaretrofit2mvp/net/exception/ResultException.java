package com.fire.demo.rxjavaretrofit2mvp.net.exception;

/**
 * Created by pc on 2016/8/23.
 */
public class ResultException extends RuntimeException {
    private int errCode;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

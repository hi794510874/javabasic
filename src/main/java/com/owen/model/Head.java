package com.owen.model;

import java.io.Serializable;

/**
 * Created by huang_b on 2017/9/13.
 */
public class Head {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private String msg;
}

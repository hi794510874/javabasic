package com.owen.model;

/**
 * Created by huang_b on 2018/3/27.
 */
public class MyThread extends Thread {

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    private String param;

    @Override
    public void run() {
        System.out.println("threadId：" + currentThread().getId() + "  param:" + param);
    }
}

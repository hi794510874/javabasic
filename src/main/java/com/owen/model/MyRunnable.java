package com.owen.model;


/**
 * Created by huang_b on 2018/3/27.
 */
public class MyRunnable implements Runnable {

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    private String param;

    public void run() {
        System.out.println("子线程ID：" + Thread.currentThread().getId() + "  param:" + param);
    }
}

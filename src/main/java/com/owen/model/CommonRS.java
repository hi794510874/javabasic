package com.owen.model;

/**
 * Created by huang_b on 2018/3/28.
 */
public class CommonRS<T> {

    private T data;
    private Head head;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

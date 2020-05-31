package com.ego.commons;

import java.io.Serializable;

public class ItemStatus implements Serializable {
    //作为商品的状态
    private int status;

    private String msg;
    private Object data;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }
}

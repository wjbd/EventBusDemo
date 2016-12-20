package com.lixingqu.eventbusdemo;

/**
 * Created by lixingqu on 2016/11/28.
 */

public class HelloMessage {
    private String msg;
    private String obj;

    public HelloMessage(String msg, String obj) {
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "HelloMessage{" +
                "msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }
}

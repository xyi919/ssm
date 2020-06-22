package com.hzit.myssm1.resp;
import com.hzit.myssm1.pojo.User;

import java.util.List;

public class UserResp {

    private int code ;
    private String msg;
    private  List<User> data;

    @Override
    public String toString() {
        return "UserResp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

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

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}

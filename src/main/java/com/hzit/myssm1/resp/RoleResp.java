package com.hzit.myssm1.resp;

import com.hzit.myssm1.pojo.Role;

import java.util.List;

public class RoleResp {

    private int code ;
    private String msg;

    public List<Role> getData() {
        return data;
    }

    public void setData(List<Role> data) {
        this.data = data;
    }

    private  List<Role> data;


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


}

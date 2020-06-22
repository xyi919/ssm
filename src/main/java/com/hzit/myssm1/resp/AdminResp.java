package com.hzit.myssm1.resp;

import java.util.List;

public class AdminResp {

    int code ;
    String msg ;
    List<AdminData>  data;

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

    public List<AdminData> getData() {
        return data;
    }

    public void setData(List<AdminData> data) {
        this.data = data;
    }



}

package com.hzit.myssm1.resp;

import java.util.List;

public class MenuResp {
    int code ;
    String msg ;
    List<MenuData>  result;

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

    public List<MenuData> getResult() {
        return result;
    }

    public void setResult(List<MenuData> result) {
        this.result = result;
    }
}

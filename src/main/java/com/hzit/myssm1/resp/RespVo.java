package com.hzit.myssm1.resp;

/**
 * 返回前端公共对
 */
public class RespVo {

    private int code ;

    private String msg;

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
    /**
     * 成功返回
     * @return
     */
    public static RespVo getSucess(String msg){
        RespVo respVo =  new RespVo();

        respVo.setCode(0);
        respVo.setMsg(msg);
        return  respVo;
    }

    /**
     * 失败返回
     * @param msg
     * @return
     */
    public static RespVo getFail(String msg){
        RespVo respVo =  new RespVo();

        respVo.setCode(-1);
        respVo.setMsg(msg);
        return  respVo;
    }

}
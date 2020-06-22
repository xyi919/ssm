package com.hzit.myssm1.service;

import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.resp.AdminData;
import com.hzit.myssm1.resp.RespVo;

import java.util.List;

public interface IAdminService {
    /**
     * 查找全部的管理员信息
     *
     * @return
     */
    List<AdminData> queryAllAdmin();

    /**
     * 根据id查找管理员
     * @return
     */
    Admin findAdminById(String id );

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    RespVo insertAdmin(Admin admin);

    /**
     * 更新管理员
     * @param admin
     * @return
     */
    RespVo updateAdmin(Admin admin );


    RespVo delAdmin (String id);

    RespVo delAdmins (String[] strings);
}

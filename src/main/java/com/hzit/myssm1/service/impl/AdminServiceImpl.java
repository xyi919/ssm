package com.hzit.myssm1.service.impl;

import com.hzit.myssm1.mapper.AdminMapper;
import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.pojo.Role;
import com.hzit.myssm1.resp.AdminData;
import com.hzit.myssm1.resp.RespVo;
import com.hzit.myssm1.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<AdminData> queryAllAdmin() {
        List<AdminData> list = adminMapper.queryAllAdmin();

        return list;
    }

    @Override
    public Admin findAdminById(String id ) {
        Admin admin =  adminMapper.findAdminById(id);
        return admin;
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @Override
    public RespVo insertAdmin(Admin admin) {
        int rlt = adminMapper.insertSelective(admin);

        if(rlt>0){
            RespVo.getSucess("添加成功");
        }
        return RespVo.getFail("添加失败");
    }

    /**
     * 更新管理员
     * @param admin
     * @return
     */
    @Override
    public RespVo updateAdmin(Admin admin) {
     int rlt =  adminMapper.updateByPrimaryKeySelective(admin);
     if(rlt>0){
         RespVo.getSucess("更新成功");
     }
        return  RespVo.getSucess("更新失败");
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @Override
    public RespVo delAdmin(String id) {

        int rlt =  adminMapper.delAdmin(id);
        if(rlt>0){
            RespVo.getSucess("更新成功");
        }
        return  RespVo.getSucess("更新失败");
    }

    /**
     * 批量删除数据
     * @param strings
     * @return
     */
    @Override
    public RespVo delAdmins(String[] strings) {
        return null;
    }


}

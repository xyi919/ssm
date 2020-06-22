package com.hzit.myssm1.service.impl;

import com.hzit.myssm1.mapper.RoleMapper;
import com.hzit.myssm1.pojo.Role;
import com.hzit.myssm1.service.IRoleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleManageImpl  implements IRoleManage {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> queryAll() {
        List<Role> list = roleMapper.queryAll();
        return list;
    }
}

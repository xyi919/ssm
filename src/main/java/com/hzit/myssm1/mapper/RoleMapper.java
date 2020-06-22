package com.hzit.myssm1.mapper;

import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查找全部的角色信息
     * @return
     */
    List<Role> queryAll();

}
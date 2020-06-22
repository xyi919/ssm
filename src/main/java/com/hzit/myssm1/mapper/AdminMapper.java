package com.hzit.myssm1.mapper;

import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.resp.AdminData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    int insertSelective(Admin admin);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin admin);

    int updateByPrimaryKey(Admin record);

    Admin selectByUserName(@Param("username") String userName);

    /**
     * 按用户名查找该用户的权限
     * @param username
     * @return
     */
    @Select("SELECT m.perms FROM tb_roles_menus r LEFT JOIN tb_menus m ON r.menu_id = m.menu_id left join tb_roles r1 on r.role_id=r1.role_id left join tb_admin a on r1.role_id=a.role_id WHERE a.username=#{username} and m.perms is not null and m.perms <> '' ")
    List<String> getPerms(String username);

    /**
     * 查找全部管理员
     * @return
     */
    @Select("select a.username,a.sex,a.phone,a.id,a.fullname,a.e_mail,a.birthday,a.address,b.role_name  from tb_admin a left join  tb_roles b  on a.role_id=b.role_id")
    List<AdminData> queryAllAdmin();


    /**
     * 根据id查找管理员
     * @return
     */
    @Select("select * from tb_admin where id=#{id}")
   Admin findAdminById(@Param("id") String  id);

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    int delAdmin(@Param("id") String id);

    /**
     * 批量删除管理员
     * @param adminStr
     * @return
     */
    int delAdmins(@Param("adminStr") String[] adminStr);
}
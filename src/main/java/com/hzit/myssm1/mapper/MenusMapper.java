package com.hzit.myssm1.mapper;

import com.hzit.myssm1.pojo.Menus;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MenusMapper {
    @Select("SELECT m.menu_id as menuId,m.title,m.icon,m.href,m.spread,m.parent_id as parentId,m.perms FROM tb_roles_menus r LEFT JOIN tb_menus m ON r.menu_id = m.menu_id WHERE r.role_id =1 order by sorting desc")
    List<Menus> getMenus();

}
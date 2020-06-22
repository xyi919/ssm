package com.hzit.myssm1.service.impl;

import com.hzit.myssm1.mapper.MenusMapper;
import com.hzit.myssm1.pojo.Menu;
import com.hzit.myssm1.pojo.Menus;
import com.hzit.myssm1.resp.MenuData;
import com.hzit.myssm1.resp.MenuResp;
import com.hzit.myssm1.service.IMenuService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询一级菜单
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenusMapper menusMapper;
    @Override
    public List<Menu> getMenus() {
        List<Menu> results = new ArrayList<>();
            List<Menus> menus = menusMapper.getMenus();
            for (int i = 0; i < menus.size(); i++) {
                if (menus.get(i).getParentId() == 0) {
                    Menu menu = new Menu();
                    menu.setTitle(menus.get(i).getTitle());
                    menu.setIcon(menus.get(i).getIcon());
                    menu.setHref(menus.get(i).getHref());
                    menu.setSpread(menus.get(i).getSpread());
                    List<Menu> menus2 = new ArrayList<>();
                    for (int j = 0; j < menus.size(); j++) {
                        if (menus.get(j).getParentId() == menus.get(i).getMenuId()) {
                            Menu menu2 = new Menu();
                            menu2.setTitle(menus.get(j).getTitle());
                            menu2.setIcon(menus.get(j).getIcon());
                            menu2.setHref(menus.get(j).getHref());
                            menu2.setSpread(menus.get(j).getSpread());
                            menus2.add(menu2);
                        }
                    }
                    menu.setChildren(menus2);
                    results.add(menu);
                }
            }

        return results;
    }
}

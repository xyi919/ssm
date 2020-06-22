package com.hzit.myssm1.controller;

import com.hzit.myssm1.resp.RoleResp;
import com.hzit.myssm1.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
@Autowired
private IUserService iUserService;

    @RequestMapping("/userList")
    public  String showUserManage(){
        logger.info("加载用户列表界面---------");
        return "user/userList";
    }
    @RequestMapping("/editUser/{uid}")
    public ModelAndView showUserEdit(@PathVariable("uid")String id, ModelAndView modelAndView){
        logger.info("加载编辑界面---------");
        modelAndView.addObject(id);
        modelAndView.setViewName("user/editUser");
        return modelAndView;
    }
    @RequestMapping("/addUser")
    public  String addUser(){
        logger.info("加载用户列表界面---------");
        return "user/addUser";
    }

    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserList")
    public RoleResp queryRoleList(){
        logger.info("开始查询");
        RoleResp resp = new RoleResp();
        List list = iUserService.queryAll();
        if(CollectionUtils.isEmpty(list)){
            resp.setCode(-1);
            resp.setMsg("查询失败");
            return  resp;
        }
        logger.info("list:{}",list);
        resp.setCode(0);
        resp.setMsg("查询成功");
        resp.setData(list);
        return resp;
    }
}

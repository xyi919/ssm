package com.hzit.myssm1.controller;

import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.resp.RespVo;
import com.hzit.myssm1.service.IAdminService;
import com.hzit.myssm1.service.IRoleManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys")
public class AdminManageController {
    private  static  final Logger logger = LoggerFactory.getLogger(AdminManageController.class);
    @Autowired
    private IRoleManage roleManage;
    @Autowired
    private IAdminService adminService;

    /**
     * 加载添加管理员的页面
     * @param modelAndView
     * @return
     */
    @RequestMapping("/addAdmin")
    public ModelAndView showAddAdmin(ModelAndView modelAndView){
        modelAndView.setViewName("admin/addAdmin");
        //查询所有的roleid和roleName
        List rolesList  = roleManage.queryAll();
        modelAndView.addObject("roles",rolesList);
        return modelAndView ;
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping(value = "/insAdmin", method = RequestMethod.POST)
    public RespVo addAdmin(@RequestBody  Admin admin){
        logger.info("添加管理员中"+admin);
        RespVo resp =  adminService.insertAdmin(admin);
        logger.info("结果"+resp.getMsg());
        return resp;
    }

    /**
     * 更新管理员
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespVo updateAdmin(@RequestBody Admin admin){
        logger.info("更新用户");
        RespVo resp =   adminService.updateAdmin(admin);
        return  resp;
    }

    /**
     * 删除管理员
     */
    @RequestMapping("/del/{id}")
    public RespVo delAdmin(@PathVariable("id") String id){
        logger.info("删除用户");
        RespVo resp = adminService.delAdmin(id);
        return  resp;
    }
    /**
     * 批量删除管理员
     */
    @RequestMapping("/delAdmins/{adminStr}")
    public RespVo delAdmins(@PathVariable("adminStr") String adminStr){
        String[] strs = adminStr.split(",");
        logger.info("删除用户");
        RespVo resp = adminService.delAdmins(strs);
        return  resp;
    }
}

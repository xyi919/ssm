package com.hzit.myssm1.controller;
import com.google.code.kaptcha.Producer;
import com.hzit.myssm1.pojo.Admin;
import com.hzit.myssm1.pojo.Menu;
import com.hzit.myssm1.resp.AdminData;
import com.hzit.myssm1.resp.AdminResp;
import com.hzit.myssm1.resp.RespVo;
import com.hzit.myssm1.resp.RoleResp;
import com.hzit.myssm1.service.IAdminService;
import com.hzit.myssm1.service.IMenuService;
import com.hzit.myssm1.service.IRoleManage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("/sys")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IRoleManage iRoleManage;
    @Autowired
    private IAdminService adminService;
    /**
     * 验证码
     */
    @Autowired
    private Producer captchaProducer = null;

    /**
     * 加载main页面
     * @return
     */
    @RequestMapping("/main")
    public String showMain(){
        return "main";
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespVo login(@RequestParam("username")String userName,
                        @RequestParam( "password")String password,
                        @RequestParam( "vcode")String vcode,
            HttpServletRequest request) {
        logger.info("用户登录请求参数vcode:{}",vcode);
        //if (StringUtils.isEmpty(vcode)){
        //    logger.info("验证码为空");
        //    return RespVo.getFail("验证码为空");
        //}
        if (StringUtils.isEmpty(password)){
            logger.info("password为空");
            return RespVo.getFail("password为空");
        }
        if (StringUtils.isEmpty(userName)){
            logger.info("userName为空");
            return RespVo.getFail("userName为空");
        }
        //String sessionCode = (String) request.getSession().getAttribute("kaptcha");
        //if(!vcode.equals(sessionCode)){
        //    return RespVo.getFail("输入验证码不正确");
        //}
        //shiro 登录逻辑 TODO
        Subject subject = SecurityUtils.getSubject();
        //将密码转成md5
        String MD5 = new Md5Hash(password).toString();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,MD5);
        //判断是否登陆
        if(!subject.isAuthenticated()){
            try{
            subject.login(usernamePasswordToken);
            }
            catch (AuthenticationException e){ //TODO
                logger.error("AuthenticationException",e);
                return RespVo.getFail("登录失败");
            }
        }

        return RespVo.getSucess("登陆成功");
    }


    /**
     * 生成验证码
     */
    @RequestMapping("/vcode")
    public void createKaptcha(HttpServletRequest request, HttpServletResponse response) {
        logger.info("生成验证码");
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);
        //放到session中,登录的时候要验证
        request.getSession().setAttribute("kaptcha", text);
        //返回验证码给前端
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    /**
     * 获取菜单
     */
    @ResponseBody
    @RequestMapping(value = "/getMenus", method = RequestMethod.GET)
    public List<Menu> getMenus() {
        logger.info("获取菜单中============================");
        List<Menu> menus = iMenuService.getMenus();

        logger.info("参数：{}", menus.get(0));
        return  menus;
    }
    @RequestMapping("/roleList")
    public  String userManage(){

        return "admin/roleList";
    }

    @RequestMapping("/adminList")
    public  String adminList(){
        return "admin/adminList";
    }


    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoleList")
    public RoleResp queryRoleList(){
        logger.info("开始查询");
        RoleResp resp = new RoleResp();
        List list = iRoleManage.queryAll();
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

    /**
     * 获取管理员列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAdminList")
    public AdminResp queryAdminList(){
        logger.info("开始查询");
        AdminResp resp = new AdminResp();
        List list = adminService.queryAllAdmin();
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

    /**
     * 加载编辑管理员页面
     * @param id
     * @param modelAndView
     * @return
     */
    @ResponseBody
    @RequestMapping("/editAdmin/{id}")
    public ModelAndView showUserEdit(@PathVariable("id")String id, ModelAndView modelAndView){
        logger.info("加载编辑界面---------");
        List roleList = iRoleManage.queryAll();
       Admin admin = adminService.findAdminById(id);
        logger.info("查询结果---------"+admin);
        modelAndView.addObject("ad",admin);
        modelAndView.addObject("roles",roleList);
        modelAndView.setViewName("admin/editAdmin");
        return modelAndView;
    }
    /**
     * 跳转编辑角色信息页面
     * @return
     */
    @RequestMapping("/editRole")
  public String editView(){
        return "admin/editRole";
  }

}
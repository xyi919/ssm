package com.hzit.myssm1.shiro;

import com.hzit.myssm1.mapper.AdminMapper;
import com.hzit.myssm1.pojo.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm  extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private AdminMapper adminMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin =(Admin)principalCollection.getPrimaryPrincipal();
        logger.info("传入的用户名"+admin.getUsername());
        List<String> list = adminMapper.getPerms(admin.getUsername());
        logger.info("2121221"+list);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        //把用户权限放到shiro中
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> stringPermissions =    new HashSet<String>();

        stringPermissions.addAll(list);

        simpleAuthorizationInfo.setStringPermissions(stringPermissions);
        logger.info("2121221"+simpleAuthorizationInfo.getStringPermissions());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String userName = (String)authenticationToken.getPrincipal();
        logger.info("userName"+userName);
        Admin admin =  adminMapper.selectByUserName(userName);
        logger.info("id是"+admin.getId());
        if(ObjectUtils.isEmpty(admin)){
            return null;
        }
        //数据库中的数据放到shiro中
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin,admin.getPassword(),"myReaml");

        return simpleAuthenticationInfo;
    }
}

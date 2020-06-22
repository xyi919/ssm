package com.hzit.myssm1.service.impl;

import com.hzit.myssm1.mapper.UserMapper;
import com.hzit.myssm1.pojo.User;
import com.hzit.myssm1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryAll() {
     List<User> list = userMapper.QueryAll();
        return list;
    }
}

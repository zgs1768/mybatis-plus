package com.gus.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.mapper.UserMapper;
import com.gus.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//serviceimpl 是 Iservice的实现 就直接可以继承Service的方法  ; userservice的方法再拓展实现
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Resource
//   private UserMapper userMapper;

    @Override
    public List<User> ListAllByName(String name) {
        //return userMapper.selectAllByName(name);
        return baseMapper.selectAllByName(name);
    }
}

package com.gus.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gus.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> ListAllByName(String name);
}

package com.gus.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gus.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository //迷惑ide我是个可以创建实体对象的类
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllByName(String name);

    /**
     * 查询 : 根据年龄查询用户列表，分页显示
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位
     * @param age 年龄
     * @return 分页对象
     */
    IPage<User> selectPageByAge(Page<?> page,Integer age);

}

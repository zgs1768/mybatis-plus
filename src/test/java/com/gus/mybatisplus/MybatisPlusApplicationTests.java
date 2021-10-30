package com.gus.mybatisplus;

import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest //自动初始化,创建spring上下文环境
class MybatisPlusApplicationTests {

//    @Autowired  按类型装配;spring注解
    @Resource  //J2EE 按名称装配,没有再按类型装配
    private UserMapper userMapper;

    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        System.out.println("git 变动测试");
        System.out.println("git 变动测试2");
        System.out.println("hotfix change");
        System.out.println("hotfix change2 -冲突合并");
        System.out.println("master  change 冲突");
    }

}

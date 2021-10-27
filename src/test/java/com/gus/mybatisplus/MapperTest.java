package com.gus.mybatisplus;

import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        //user.setAge(300);
        user.setEmail("300@Foxmail.com");
        user.setName("人生300");
        //user.setCreateTime(LocalDateTime.now());
        //user.setUpdateTime(LocalDateTime.now());
        int result = userMapper.insert(user);
        System.out.println(result);

    }

    @Test
    public void testSelect(){
        User user = userMapper.selectById(3);
        System.out.println(user);

        List<User> users = userMapper.selectBatchIds(Arrays.asList("1", "2"));
        users.forEach(System.err::println);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "鲨鱼");
        map.put("age", "99");
        List<User> users1 = userMapper.selectByMap(map); //and 条件
        users1.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1434862570904350721L);
        user.setName("UP22");
        userMapper.updateById(user); //属性为null 不会修改
    }

    @Test
    public void testDelete(){
        int i = userMapper.deleteById(2);
        System.out.println(i);

    }

    @Test
    public void testSelectAllByName(){
        List<User> shark = userMapper.selectAllByName("鲨鱼");
        shark.forEach(System.out::println);

    }



}

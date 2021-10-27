package com.gus.mybatisplus;

import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Resource
    private UserService userService;


    @Test
    public void testCount(){
        int count = userService.count();
        System.out.println("总记录数:"+count);
    }

    @Test
    public void testSaveBatch(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("小米"+i);
            user.setAge(20+i);
            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }

    @Test
    public void testListAllByName(){
        List<User> tom = userService.ListAllByName("tom");
        System.out.println(tom);
    }
}

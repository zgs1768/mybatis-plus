package com.gus.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Resource
    private UserMapper userMapper;


    //    查询名字中包含n，
//    年龄大于等于10且小于等于20，
//    email不为空的用户
    @Test
    public void test1() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //对应数据库列名,非属性名
        userQueryWrapper.like("username", "小")
                .between("age", 20, 50)  //这里是包括等于
                .isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    //排序
    @Test
    public void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    //删除
    @Test
    public void test3() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("email");
        int delete = userMapper.delete(userQueryWrapper);
        System.out.println(delete);

    }

    //条件优先级
//    查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
    @Test
    public void test4() {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", "人")
                .and(i ->
                        i.lt("age", 50)
                                .or().isNull("email"));

        User user = new User();
        user.setAge(18);
        user.setEmail("user@haha.com");

        int update = userMapper.update(user, userQueryWrapper);
        System.out.println(update);

    }

    //查询用户名和年龄
    @Test
    public void test5() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("username", "age");
        // List<User> users = userMapper.selectList(userQueryWrapper);
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);

    }

    //子查询
    @Test
    public void test6() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //userQueryWrapper.inSql("uid" , "select uid from t_user where uid <= 3");
//        userQueryWrapper.in("uid", 1,2,3);
        userQueryWrapper.le("uid", 3);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);

    }

    //条件优先级
//    查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
    @Test
    public void test7() {

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .set("age",20)
                .set("email", "renzhichu.@ren.com")
                .like("username", "人")
                .and(i ->
                        i.lt("age", 50)
                                .or().isNull("email"));

//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@haha.com");

        //告诉更新的对象,传递时可以更新对应拦截器的字段
        User user = new User();
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println(update);

    }






}

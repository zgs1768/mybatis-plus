package com.gus.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gus.mybatisplus.entity.Product;
import com.gus.mybatisplus.entity.User;
import com.gus.mybatisplus.mapper.ProductMapper;
import com.gus.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testConcurrentUpdate(){
        //小李取数据
        Product p1 = productMapper.selectById(1);

        //小王取数据
        Product p2 = productMapper.selectById(1L);

        //小李+50
        p1.setPrice(p1.getPrice() + 50);
        int rs = productMapper.updateById(p1);
        System.out.println("小王"+rs);

        //小王-30
        p2.setPrice(p2.getPrice() -30);
        int rs2 = productMapper.updateById(p2);
        System.out.println("小李"+rs2);
        if (rs2 == 0){
            Product p3 = productMapper.selectById(1L);
            p3.setPrice(p3.getPrice() -30);
            int rs3 = productMapper.updateById(p3);
            System.err.println(rs3);
        }

        //最终价格
        Product p3 = productMapper.selectById(1);
        System.out.println(p3);

    }

    @Test
    public void selectPageTest(){
        //分页参数对象
        Page<User> pageParam = new Page<>(1,5);
        userMapper.selectPage(pageParam, null);
        List<User> records = pageParam.getRecords();
        System.out.println(records);
        System.out.println(pageParam.getTotal());
        System.out.println("有下一页"+pageParam.hasNext());
        System.out.println("有上一页"+pageParam.hasPrevious());
    }

    @Test
    public void selctPageByAgeTest(){
        Page<User> userPage = new Page<>(2, 2);
        userMapper.selectPageByAge(userPage, 77);
        userPage.getRecords().forEach(System.out::println);
    }

}

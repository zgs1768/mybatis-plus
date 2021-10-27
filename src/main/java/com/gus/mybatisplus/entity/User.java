package com.gus.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user")  //basemapper根据实体类找到对应的表,如果没有就用此注解标注对应表名
public class User {

    //默认雪花算法
    //@TableId(type = IdType.ASSIGN_ID)
    //主键自增,需要数据库配合
   // @TableId(type = IdType.AUTO)

    @TableId(value = "uid") //value值也可以对应数据库列名
    private Long id;
    //mybaitsPlus只对默认为id 的值生成 否则需要@TableId注解告诉uid为id
    @TableField(value = "username")
    private String name;
    @TableField(fill = FieldFill.INSERT)
    private Integer age;
    private String email;

    //@TableField(value = "create_time") mybatis会对这汇总命名自动转换
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic  //注解该字段逻辑删除
    @TableField(value = "is_deleted")
    private Integer deleted;
    //private Boolean deleted; 都可以 mysql数据库底层无布尔类型
    // 0-false-未删-可用  1-true-已删除-不可用


    //为什么建议使用你 LocalDateTime ，而不是 Date？https://zhuanlan.zhihu.com/p/87555377
    //java.util.Date的大多数方法已经过时
    //java.util.Date的输出可读性差
    //java.util.Date对应的格式化类SimpleDateFormat是线程不安全的类。阿里巴巴开发手册中禁用static修饰SimpleDateFormat。
    //LocalDateTime 对应的格式化类DateTimeFormatter是线程安全的

}

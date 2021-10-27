package com.gus.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//几乎所有的数据库都有值时才自动填充

@Component
@Slf4j
public class MyMetaObjectHandler  implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert 自动填充..........");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());


        Object age = this.getFieldValByName("age", metaObject);
        if (age == null) {
            log.info("insert 自动填充年龄..........");
            this.strictInsertFill(metaObject, "age", Integer.class, 5);
        }

        if (metaObject.hasSetter("author")) {
            log.info("其他类的属性填充--------------");
            this.strictInsertFill(metaObject, "author", String.class, "煞笔");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update 自动修改..........");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

    }
}

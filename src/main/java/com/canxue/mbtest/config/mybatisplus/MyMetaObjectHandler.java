package com.canxue.mbtest.config.mybatisplus;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author canxue
 * 公共字段填充.
 * 暂时不需要知道
 * @create2018-10-10-11:31
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
 private static final Logger LOGGER =LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        Object fieldValByName = getFieldValByName("roleDescribe", metaObject);
        if (fieldValByName==null){
            LOGGER.info("填入默认值");
            setFieldValByName("roleDescribe","wwwwwww",metaObject);
        }


    }

    @Override
public void updateFill(MetaObject metaObject) {
    Object fieldValByName = getFieldValByName("roleDescribe", metaObject);
    if (fieldValByName==null){
        setFieldValByName("roleDescribe","wwwwwww",metaObject);
    }
}
}

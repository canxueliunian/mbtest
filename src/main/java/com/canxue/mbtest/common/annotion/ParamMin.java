package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * <= 比较场景
 * * value为数据表对应列名,不能为空
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamMin {

    String value();
}

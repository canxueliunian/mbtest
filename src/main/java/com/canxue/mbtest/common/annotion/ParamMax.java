package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * 字段结束时间
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamMax {
    String value() ;
}

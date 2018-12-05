package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * 使用于需要进行时间大于等于的字段尚
 * 其中balue值为数据库对应字段的名称
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamMin {

    String value() ;
}

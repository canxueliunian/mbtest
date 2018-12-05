package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * 使用于需要模糊查询的属性上
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClumnLike {

}

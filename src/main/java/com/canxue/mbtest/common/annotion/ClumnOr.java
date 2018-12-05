package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * 使用于需要模糊查询的属性上,
 * 其中value值为查询的字段名称--数据库字段名称
 * 设置value的话则去value的值, 不设置的话则反射属性名生成
 *
 * 约定: 如果设置value的话则把其作为列名,当传递一个值时,
 * 按照and 来进行拼接, 当传递多个的时候, 按照字符串逗号分隔的形式来进行分割.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClumnOr {
    String value() default "";
}

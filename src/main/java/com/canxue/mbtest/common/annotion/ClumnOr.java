package com.canxue.mbtest.common.annotion;


import java.lang.annotation.*;

/**
 * Created by viruser on 2017/6/20.
 * 用于需要or查询拼接的属性上
 * value--表列名, 为空时,驼峰转换属性名得出
 * ---
 * 传入多个数据使用,分割,后台会进行解析处理
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClumnOr {
    String value() default "";
}

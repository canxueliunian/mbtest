package com.canxue.mbtest.common.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.canxue.mbtest.common.annotion.ClumnLike;
import com.canxue.mbtest.common.annotion.ClumnOr;
import com.canxue.mbtest.common.annotion.ParamMin;
import com.canxue.mbtest.common.annotion.ParamMax;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lishuntao on 2018/11/26.
 * 反射处理条件 , 生成queryWrapper工具类
 */
public class DealDataUtil {
    public static void DealData(QueryWrapper queryWrapper, Object data) {
        try {
            if (data == null) {
                return;
            }
            Class<?> aClass = data.getClass();
            Field[] fields = aClass.getDeclaredFields();
            // todo 待校验 queryWrapper 放置进1=1 放置后续的条件拼接发生错误

            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                Object fieldValue = getFieldValue(data, field);
                if (fieldValue == null) {
                    continue;
                }
                // 处理模糊查询
                if (field.isAnnotationPresent(ClumnLike.class)) {
                    // 反射获取列名
                    String clumnName = field.getName();
                    clumnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clumnName);
                    queryWrapper.like(clumnName, fieldValue);

                    continue;
                }
                // 起始参数
                if (field.isAnnotationPresent(ParamMin.class)) {
                    ParamMin annotation = field.getAnnotation(ParamMin.class);
                    String clumnName = annotation.value();
                    queryWrapper.ge(clumnName, fieldValue);
                    continue;
                }
                // 结束参数
                if (field.isAnnotationPresent(ParamMax.class)) {
                    ParamMax annotation = field.getAnnotation(ParamMax.class);
                    String value = annotation.value();
                    queryWrapper.le(value, fieldValue);
                    continue;
                }
                // 处理or条件查询
                if (field.isAnnotationPresent(ClumnOr.class)) {
                    try {
                        String fieldString = (String) fieldValue;
                        if (StringUtils.isEmpty(fieldString)) {
                            continue;
                        }
                        ClumnOr annotation = field.getAnnotation(ClumnOr.class);
                        String clumnName = annotation.value();
                        if (StringUtils.isEmpty(clumnName)) {
                            clumnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                        }
                        // 解析成List
                        List<String> result = Arrays.asList(fieldString.split(","));
                        if (result.size() == 1) {
                            queryWrapper.eq(clumnName, result.get(0));
                        }
                        // 处理并拼接sql语句
                        // 第一个条件需要使用and 来进行拼接,后续的才应该为or拼接
                        String first = result.remove(0);
                        queryWrapper.eq(clumnName, first);
                        // 后续条件使用or 来进行拼接.
                        for (String or : result) {
                            queryWrapper.or();
                            queryWrapper.eq(clumnName, or);
                        }
                        continue;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 处理普通的字段内容
                boolean exit = field.isAnnotationPresent(TableField.class);
                if (!exit) {
                    String clunmName = field.getName();
                    clunmName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clunmName);
                    queryWrapper.eq(clunmName, fieldValue);

                } else {
                    TableField annotation = field.getAnnotation(TableField.class);
                    boolean exist = annotation.exist();
                    if (exist == true) {
                        String clunmName = field.getName();
                        clunmName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clunmName);
                        queryWrapper.eq(clunmName, fieldValue);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取属性值
    private static Object getFieldValue(Object model, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        field.setAccessible(true);
        return field.get(model);
        //return model.getClass().getMethod(getMethodeNameByFiledName(field.getName())).invoke(model);
    }
}

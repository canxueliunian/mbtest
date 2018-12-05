package com.canxue.mbtest.common.util;


import com.google.common.base.CaseFormat;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by viruser on 2017/6/
 * Map工具类, 可以将对象直接转换成合适的map
 */
public class MapUtils {

    // Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
    public static void transMap2Bean2(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }
    }

    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static void transMap2Bean(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

        return;

    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    //进行校验方法,如果属性值正常, 则放入到map之中, 如果属性值不正常则不放入map中
                    boolean flag = check(value);
                    if (flag) {
                        map.put(key, value);
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2MapWithCamel(Object obj, List<String> removeList) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (removeList.contains(key)) {
                    continue;
                }

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    //进行校验方法,如果属性值正常, 则放入到map之中, 如果属性值不正常则不放入map中
                    boolean flag = check(value);
                    if (flag) {
                        key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
                        map.put(key, value);
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj, String... remove) {
        List<String> removeList = Arrays.asList(remove);
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                //如果里面包含其, 则将其跳过, 以达到封装去除属性的目的
                if (removeList.contains(key)) {
                    continue;
                }
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    //进行校验方法,如果属性值正常, 则放入到map之中, 如果属性值不正常则不放入map中
                    boolean flag = check(value);
                    if (flag) {
                        map.put(key, value);
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * 检测map中是否有其List中的属性名称
     *
     * @param obj
     * @param list
     * @return
     */
    public static Map<Boolean, String> checkNeed(Object obj, List<String> list) {
        Map<Boolean, String> resultMap = new HashMap<Boolean, String>();
        if (obj == null) {
            resultMap.put(false, "检查对象为空");
            return resultMap;
        }
        if (isNullOrEmpty(list)) {
            resultMap.put(false, "检查List为空");
            return resultMap;
        }
        StringBuffer buffer = new StringBuffer();
        Map<String, Object> stringObjectMap = MapUtils.transBean2Map(obj);
        for (String key : list) {
            Object value = stringObjectMap.get(key);
            if (!check(value)) {
                buffer.append(key + "属性错误");
                resultMap.put(false, buffer.toString());
            }
            ;
        }
        if (resultMap.size() == 0) {
            resultMap.put(true, "属性不缺失");
        }
        return resultMap;
    }

    /**
     * 判断单个类型是否正常,true则可以存储到map中, false的话则不能存储到map中
     *
     * @param value
     * @return
     */
    public static boolean check(Object value) {
        //判断是否为null
        if (value == null)
            return false;
        //判断是否为String
        if (value instanceof String) {
            String value1 = (String) value;
            if (value1.equals("")) {
                return false;
            }
            return true;
        }

        //判断各个集合类型
        if (value instanceof List) {
            if (((List) value).size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        //判断各个集合类型
        if (value instanceof Map) {
            if (((List) value).size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        //判断各个集合类型
        if (value instanceof Set) {
            if (((List) value).size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 判断传入的map是否为null或者长度为0
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmpty(Object object) {
        if (object == null)
            return true;
        if (object instanceof Map) {
            Map map = (Map) object;
            if (map != null && map.size() != 0) {
                return false;
            }
        }
        if (object instanceof List) {
            List list = (List) object;
            if (list != null && list.size() != 0) {
                return false;
            }
        }
        if (object instanceof Set) {
            Set set = (Set) object;
            if (set != null && set.size() != 0) {
                return false;
            }
        }
        return true;
    }

    ;

    /**
     * 将map中长度类型为空的集不符合的条件删除
     *
     * @param map
     * @return
     */
    public static void removeNull(Map map) {
        if (isNullOrEmpty(map)) {
            return;
        }
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while ((iterator.hasNext())) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            boolean check = check(value);
            if (!check) {
                iterator.remove();
            }


        }
    }


}

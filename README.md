#markdowm
 **测试**
---

# 反射相关代码

- 获取列结合
 `Field[] fields = aClass.getDeclaredFields();`
 - 获取列的注解集合
 `   Annotation[] annotations = field.getAnnotations();`
 - 获取列的值
 (```)
 //获取属性值
    private static Object getFieldValue(Object model, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        field.setAccessible(true);
        return field.get(model);
        //return model.getClass().getMethod(getMethodeNameByFiledName(field.getName())).invoke(model);
    }
 (```)
 - 驼峰规则转换
 ` clumnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clumnName);`
 - 获取注解:
 `    ParamMin annotation = field.getAnnotation(ParamMin.class);`
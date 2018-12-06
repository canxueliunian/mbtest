#mybatisplus test项目说明
---
##1.使用技术:
  该项目主要使用spring boot + mybatis plus +注解反射
##2.Vedio Message:
  - 该项目主要为配合视频讲解mybatis plus 技术的使用,以及在mybatis plus的基础上结合注解反射
  实现自动封装复杂查询语句, 生成查询所编写
  - 项目基础且简单.
  ---
##**3.spring boot 相关背景知识:**
- spring boot 是在spring基础上进行的加强简化,本质还是spring,目前常用于微服务项目
- application.yml
    spring boot 推崇配置文件格式(*使用传统的application.properties也 是可以的*)
    其文件形式简洁,同时有包含了配置的结构信息
- spring boot中的配置:
    spring boot 会在项目启动时进行大量的自动配置
    application.yml可以做大量的配置
    spring boot 更推崇使用java bean的形式进行配置,而非xml配置文件的格式
- web 项目启动部署:
    我们看一下这里的项目debug配置啊,并不是我们熟悉的tomcat啊,它也不是打war包然后tomcat部署.
    而是打jar包,直接运行的,原理我们不讲.
    spring boot的话都有一个启动类, 可以使用启动类直接运行项目
---
##**4.java反射常用方法**
- 获取属性结合
 `Field[] fields = aClass.getDeclaredFields();`
- 获取属性上的注解集合
 `   Annotation[] annotations = field.getAnnotations();`
- 获取属性的名称
`filed.getName()`
 - 获取列的值
 ```
 //获取属性值
    private static Object getFieldValue(Object model, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        field.setAccessible(true);
        return field.get(model);
        //return model.getClass().getMethod(getMethodeNameByFiledName(field.getName())).invoke(model);
    }
 ```
 - 判断是否包含注解:
 `field.isAnnotationPresent(ClumnOr.class)`
 - 获取注解:
 `    ParamMin annotation = field.getAnnotation(ParamMin.class);`
 - 驼峰规则转换
 ` clumnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clumnName);`

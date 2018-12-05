package com.canxue.mbtest;


import com.canxue.mbtest.common.util.PropertiesUtils;
import com.canxue.mbtest.entity.Student;
import com.canxue.mbtest.mapper.GeneratorMapper;
import com.canxue.mbtest.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MbtestApplicationTests {
    private static  final String FILE_NAME="Mybatis-Plus.properties";


    @Test
    public void contextLoads() {

    }

    @Resource
    GeneratorMapper generatorMapper;

    /**
     * 进行全表生成的时候,获取全部表名,并截取成逗号分隔string格式,将其写入配置文件
     */
    @Test
    public void buildTableNameList() {
        try {
            // 获取全部表信息
            List<Map<String, Object>> list = generatorMapper.list();
            List<String> tableNameList = new ArrayList<>();
            for (Map<String, Object> table : list) {
                tableNameList.add((String) table.get("tableName"));
            }
            // 生成表名String
            String tableNameString = String.join(",", tableNameList);

            // 将表名写入到配置文件中
            Properties read = PropertiesUtils.read(FILE_NAME);

            PropertiesUtils.writeValueByKey(FILE_NAME,"table_name_list",tableNameString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    StudentService studentService;
    @Test
    public void testStudent(){
        Student student = new Student();
        student.setName("yiyi");
        student.setAge(16);
        student.setEmail("yiyi@qq.com");
        studentService.save(student);
        System.out.println(student.getId());
    }


}

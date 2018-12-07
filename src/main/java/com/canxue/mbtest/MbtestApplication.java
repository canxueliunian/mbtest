package com.canxue.mbtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 需要设置mappe接口文件扫描路径
 */
@SpringBootApplication
@MapperScan("com.canxue.mbtest.mapper")
public class MbtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtestApplication.class, args);
    }
}

package com.canxue.mbtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.canxue.mbtest.mapper")
public class MbtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtestApplication.class, args);
    }
}

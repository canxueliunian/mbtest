package com.canxue.mbtest.config.mybatisplus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by lishuntao on 2018/11/22.
 */
@Configuration
public class MvcConfig {

    /**
     * 配置返回视图 json数据, 字段为null的情况下不进行设置
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        JsonObjectMapper mapper = new JsonObjectMapper();
        TimeZone china = TimeZone.getTimeZone("GMT+:08:00");
        mapper.setTimeZone(china);
        converter.setObjectMapper(mapper);
        return converter;
    }

}

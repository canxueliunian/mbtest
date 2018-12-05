package com.canxue.mbtest.config.mybatisplus;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisMapperRefresh;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis plus 配置类
 * mapperScan   --- mapper接口位置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.canxue.mbtest.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 不配置的话则在查询时不使用limit来分页, 而是内存分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 性能分析插件
     * 控制台sql 日志格式化, 时间分析, 等内容
     *
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        return sqlExplainInterceptor;
    }

    /**
     * 乐观锁插件
     * 乐观锁作用: 进行修改数据时, 先记录数据的version 列的值, 在提交的时候,对比该version值, 如果相同则成功, 不同则失败,
     * 为了确保在修改数据期间无其他人对数据进行修改
     */

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除功能
     * 逻辑软删除通常是讲status置为-1, 在通常查询时也是查找status为0的数据
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


//    @Bean
//    public MybatisMapperRefresh mybatisMapperRefresh() {
//        ClassPathResource classPathResource= new ClassPathResource("");
//        Resource[] resources= new Resource[]{};
//        resources[0]=classPathResource;
//
//        MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(resources , sqlSessionFactory,5,10,true);
//        return mybatisMapperRefresh;
//    }


}
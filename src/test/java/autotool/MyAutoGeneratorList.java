package autotool;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.canxue.mbtest.mapper.GeneratorMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by lishuntao on 2018/11/30.'
 * 读取整个表, 生成所有的表内容
 * 使用步骤: 先调用MbtestApplicationTests.buildTableNameList()方法, 生成全部的表名集合到配置文件中
 * 之后再调用main方法即可
 */
@Component
public class MyAutoGeneratorList {

    public static void main(String args[]) {
        ResourceBundle rb = ResourceBundle.getBundle("Mybatis-Plus");
        //1.全局策略配置
        //路径部分进行判断获取, 如果能获取到则按照获取到的生成, 不能的话则使用当前项目的地址.

        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(new Boolean(rb.getString("activeRecord")));
        //作者设置
        config.setAuthor(rb.getString("author"));
        config.setOpen(false);
        // 设置输出路径
//        config.setOutputDir("C:/Users/canxue/Desktop/mybatis-plus-samples-master/mbtest/src/main/java");
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/src/main/java");
        boolean outputFlag = new Boolean(rb.getString("outputDirFlag"));
        if (outputFlag) {
            config.setOutputDir(rb.getString("outputDir"));
        }
        //是否文件覆盖
        config.setFileOverride(false);
        //主键策略
        config.setIdType(IdType.AUTO);
        // 设置Service接口的首字母是否为i ,其默认生成的首字母为i
        config.setServiceName("%sService");
//        entity名称默认不加
//        config.setEntityName("%sEntity");
        //默认mapper后缀
//        config.setMapperName("%sDao");

        // 是否生成自定义的结果集
        config.setBaseResultMap(true);
        // 是否生成column列的集合
        config.setBaseColumnList(true);
        // xml中开启二级缓存
        config.setEnableCache(true);
        config.setDateType(DateType.TIME_PACK);


        //2.数据源的配置 , 配置驱动, 以及连接信息
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName(rb.getString("driver-class-name"))
                .setUrl(rb.getString("url"))
                .setUsername(rb.getString("username"))
                .setPassword(rb.getString("password"));


        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        //全局大小写命名
        stConfig.setCapitalMode(true);
        // 驼峰映射
        stConfig.setNaming(NamingStrategy.underline_to_camel);
        // 表明前缀
        stConfig.setTablePrefix("tbl_");
        // 设置生成的表

        // 设置为lomback注解形式
        stConfig.setEntityLombokModel(new Boolean(rb.getString("lombok")));
        // 设置controller的父类名称
        stConfig.setSuperControllerClass("com.canxue.mbtest.common.BaseController");


        //4.包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(rb.getString("parent"));
        pkConfig.setMapper("mapper");
        pkConfig.setService("service");
        pkConfig.setEntity("entity");
        // 设置xml生成文件的位置
        pkConfig.setXml(rb.getString("xml"));
//        MyAutoGeneratorList myAutoGeneratorList = new MyAutoGeneratorList();
        String tableNameList =
                rb.getString("table_name_list");
        List<String> tableNameString = Arrays.asList(tableNameList.split(","));
        // 遍历表名进行生成
        for (String tableName : tableNameString) {
            stConfig.setInclude(tableName);
            //5.整合配置.
            AutoGenerator autoGenerator = new AutoGenerator();
            autoGenerator.setGlobalConfig(config)
                    .setDataSource(dsConfig)
                    .setStrategy(stConfig)
                    .setPackageInfo(pkConfig);
            autoGenerator.execute();

        }


    }


}

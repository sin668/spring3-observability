package com.acme.observability.demo.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
// import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.*;
import org.junit.jupiter.api.Test;
// import cn.hutool.setting.dialect.Props;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.acme.observability.orm.entity.BaseEntity;


/**
 * MySQL 代码生成
 * 参考 https://blog.csdn.net/GoodburghCottage/article/details/130240646?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-130240646-blog-140371254.235^v43^pc_blog_bottom_relevance_base2&spm=1001.2101.3001.4242.2&utm_relevant_index=4
 * @author lanjerry
 * @since 3.5.3
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 父包名
     */
    private static final String PARENT_PATH = "com.acme.observability";

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
        .Builder("jdbc:mysql://172.18.188.97:3306/ycp4_sys?useUnicode=true&amp&characterEncoding=UTF-8&serverTimezone=GMT%2b8", "dba", "Ycp12345!!")
        .schema("ycp4_sys")
        .build();

     /**
     * 初始化全局配置
     */
    private static GlobalConfig initGlobalConfig(String projectPath) {
        return new GlobalConfig.Builder()
                .outputDir(projectPath + "/src/main/java")
                .author("Brian Lin")  
                .commentDate("yyyy-MM-dd")
                .build();
    }

    /**
     * 初始化包配置
     */
    private static PackageConfig initPackageConfig(String projectPath,String moduleName) {
        // Props props = new Props("generator.properties");
        // Map<OutputFile, String> pathInfo = new HashMap();
        // pathInfo.put(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/" + moduleName);
        // pathInfo.put(OutputFile.other,     projectPath + "/src/main/resources/other/" + moduleName);
        return new PackageConfig
                .Builder()
                .parent(PARENT_PATH)  // 父包名
                .moduleName(moduleName)      //父包模块名
                .entity("entity")             //实体类 Entity 包名,默认值：entity
                .service("service")          //Service 包名,默认值：entity
                .serviceImpl("service.impl") //实现类 Service Impl 包名	默认值：service.impl
                .mapper("mapper")            //Mapper 包名	默认值：mapper
                // .xml("mapper.xml")           //Mapper XML 包名	默认值：mapper.xml
                .controller("web")    //Controller 包名	默认值：controller
                // .other("other")              //自定义文件包名	可使用"other"，生产一个other文件目录
                // .pathInfo(pathInfo)          //路径配置信息
                .build();
    }

    /**
     * 初始化策略配置
     */
    private static StrategyConfig initStrategyConfig() {
        return new StrategyConfig.Builder()
                .likeTable(new LikeTable("district"))
                .entityBuilder()
                .superClass(BaseEntity.class)
                .enableLombok()
                .enableTableFieldAnnotation()
                .addSuperEntityColumns("id", "creator_id", "date_created", "updator_id", 
                        "date_updated", "deleted", "date_deleted")
                // .addIgnoreColumns("age")
                // .addTableFills(new Column("create_time", FieldFill.INSERT))
                // .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                // .idType(IdType.AUTO)
                .formatFileName("%sDO")
                .controllerBuilder()
                .enableRestStyle()
                .mapperBuilder()
                .enableMapperAnnotation()
                .build();
    }

    /**
    * 初始化自定义配置
    */
    private static InjectionConfig initInjectionConfig(String projectPath) {
        /**自定义生成模板参数**/
        Map<String,Object> customMap = new HashMap<>();
        // customMap.put("dto", PARENT_PATH + ".dto");
        customMap.put("vo", PARENT_PATH + ".vo");
        List<CustomFile> customFiles = new ArrayList<>();
        // customFiles.add(new CustomFile.Builder().packageName("dto").fileName("DTO.java")
        //                     .templatePath("/templates/dto.java.vm").enableFileOverride().build());
        customFiles.add(new CustomFile.Builder().packageName("vo").fileName("VO.java")
                            .templatePath("/templates/vo.java.vm").enableFileOverride().build());
        // 自定义配置
        return new InjectionConfig.Builder()
                .customMap(customMap)
                .customFile(customFiles)
                .build();
    }


    @Test
    public void testSimple() {
        String projectPath = System.getProperty("user.dir");
        String moduleName = "demo";
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        // generator.global(globalConfig().build());
        generator.global(initGlobalConfig(projectPath));
        // 包配置，如模块名、实体、mapper、service、controller等
        generator.packageInfo(initPackageConfig(projectPath, moduleName));
        // 自定义配置
        // generator.injection(initInjectionConfig(projectPath));
        // generator.strategy(strategyConfig().build());
        generator.strategy(initStrategyConfig());
        generator.execute();
    }
}
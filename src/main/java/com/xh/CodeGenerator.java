package com.xh;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成工具
 *
 * @author xiaohe
 */
public class CodeGenerator {


    public static void main(String[] args) {
        String[] tables = {"sys_user", "sys_user_role", "sys_role", "sys_menu", "sys_role_menu","company"};

        String author = "xiaohe";
        String prefix = "com.xh";
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/test_shiro?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT%2B8";
        String dbName = "root";
        String dbPassword = "accp";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("User.dir");
        if(StringUtils.isBlank(projectPath)){
            projectPath = "F:/IdeaProjects/shiro-demo";
        }
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbName);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();
        pc.setParent(prefix);
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String finalProjectPath = projectPath;
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return finalProjectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
       mpg.setTemplate(new TemplateConfig().setXml(null));

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setInclude(tables);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
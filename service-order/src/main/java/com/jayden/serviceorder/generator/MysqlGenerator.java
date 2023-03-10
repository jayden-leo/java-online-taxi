package com.jayden.serviceorder.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码的工具类
 */
public class MysqlGenerator {

    public static void main(String[] args){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-order?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root","root")
                .globalConfig(builder -> {
                   builder.author("jayden").fileOverride().outputDir("D:\\Projects\\JavaProjects\\java-online-taxi\\java-online-taxi-public\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.jayden.serviceorder").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "D:\\Projects\\JavaProjects\\java-online-taxi\\java-online-taxi-public\\service-order\\src\\main\\java\\com\\jayden\\serviceorder\\mapper"));
                })
                .strategyConfig(builder -> {
//                    builder.addInclude("car");
                    builder.addInclude("order_info");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

}

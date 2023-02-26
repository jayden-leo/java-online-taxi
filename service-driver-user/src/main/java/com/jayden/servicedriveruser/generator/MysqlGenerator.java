package com.jayden.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码的工具类
 */
public class MysqlGenerator {

    public static void main(String[] args){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root","root")
                .globalConfig(builder -> {
                   builder.author("jayden").fileOverride().outputDir("D:\\Projects\\JavaProjects\\java-online-taxi\\java-online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.jayden.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "D:\\Projects\\JavaProjects\\java-online-taxi\\java-online-taxi-public\\service-driver-user\\src\\main\\java\\com\\jayden\\servicedriveruser\\mapper"));
                })
                .strategyConfig(builder -> {
//                    builder.addInclude("car");
                    builder.addInclude("driver_car_binding_relationship");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

}

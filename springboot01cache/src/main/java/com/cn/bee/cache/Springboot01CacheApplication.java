package com.cn.bee.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搭建基本环境
 * 1.导入数据库文件，创建department和employee表
 * 2.创建javaBean封装数据
 * 3.整合Mybatis操作数据库
 *  1）配置数据源信息
 *  2）使用注解版的mybatis
 *    ①@MapperScan指定需要扫描的mapper接口所在的包
 */
@MapperScan("com.cn.bee.cache.mapper")
@SpringBootApplication
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}
}
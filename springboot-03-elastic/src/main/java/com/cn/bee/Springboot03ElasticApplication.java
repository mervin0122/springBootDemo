package com.cn.bee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术与ES交互
 * 1.Jest（默认不生效）
 *  需要导入jest的工具包(io.searchbox.client.JestClient)
 * 2.SpringData ElasticSearch[ES版本有可能不合适]
 *    版本适配说明:https://github.com/spring-projects/spring-data-elasticsearch
 *    如果不适配：【安装2.4.6】
 *       ①、升级SpringBoot版本
 *       ②、安装对应版本ES
 *   1)Client 节点信息clusterNodes、clusterName
 *   2）ElastricsearchTemplate操作es
 *   3）编写ElastricsearchRepository的子接口操作ES
 *   两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *   ①编写ElastricsearchRepository
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03ElasticApplication.class, args);
	}
}

package com.cn.bee.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *一、搭建基本环境
 * 1.导入数据库文件，创建department和employee表
 * 2.创建javaBean封装数据
 * 3.整合Mybatis操作数据库
 *  1）配置数据源信息
 *  2）使用注解版的mybatis
 *    ①@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *    步骤：1.开启基于注解的缓存@EnableCaching
 *          2.标注缓存注解即可
 *          @Cacheable
 *          @CacheEvict
 *          @CachePut
 * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache：将数据保存在ConcurrentMap<Object,Object>
 * 开发中使用缓存中间件：redis、memcached、ehcache
 * 三、整合redis作为缓存（它可用作数据库、缓存、消息中间件）
 * 1、安装redis：
 * 2.引入redis的starter
 *      <dependency>
 * 		<groupId>org.springframework.boot</groupId>
 * 		<artifactId>spring-boot-starter-data-redis</artifactId>
 * 		</dependency>
 * 3、配置redis
 * 4.测试缓存
 * 		原理：CacheManager==Cache 缓存组件实际给缓存中存储数据
 * 	    1）引入redis的starter，容器中保存的是RedisCacheManager
 * 	    2）容器中保存的是RedisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据
 * 	    3）默认保存数据k-v都是Object，利用序列化保存。
 * 	      如何保存json
 * 	       ①引入redis的starter,cacheManager变为 RedisCacheManager
 * 	       ②默认创建的RedisCacheManager操作redis的时候使用的是RedisTemplate<Object,Employee>
 * 	       ③RedisTemplate<Object,Employee>是默认使用jdk的序列化机制
 * 	       ④自定义CacheManager
 */
@MapperScan("com.cn.bee.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}
}

package com.cn.bee.cache.config;

import com.cn.bee.cache.bean.Department;
import com.cn.bee.cache.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * Created by mervin on 2018/7/1.
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
        RedisTemplate<Object,Employee> template=new RedisTemplate<Object,Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> ser=new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }
    @Bean
    public RedisTemplate<Object,Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
        RedisTemplate<Object,Department> template=new RedisTemplate<Object,Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> ser=new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(ser);
        return template;
    }
    //CacheManagerCustomizers可以用来定制缓存的一些规则
    @Primary //将某个缓存管理器作为默认的
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> empRedisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(empRedisTemplate);
        //key多了前缀，使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return  cacheManager;
    }
    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object,Department> deptRedisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(deptRedisTemplate);
        //key多了前缀，使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return  cacheManager;
    }
}

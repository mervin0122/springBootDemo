package com.cn.bee.cache.config;

import com.cn.bee.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> empRedisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(empRedisTemplate);
        //key多了前缀，使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return  cacheManager;
    }
}

package com.cn.bee.cache.service;

import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by mervin on 2018/6/25.
 */
@CacheConfig(cacheNames="emp",cacheManager = "employeeCacheManager") //抽取缓存的公共配置
@Service
public class EmployeeService
{
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存获取，不用调用方法
     * CacheManager管理多个Cahche组件的，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件都有自己唯一一个名字
     * 几个属性：
     *   cacheName/value:指定缓存组件的名字，将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *          key：缓存数据使用的key，可以用它来指定。默认是使用方法参数的值 1-方法的返回值
     *              编写SpEL, #id;参数id 的值  #a0 # p0 #root.arg[0]
     *          keyGenerator:key的生成器，可以自己指定key的生成器的组件id
     *              key/keyGenerator:二选一使用
     *          keyManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *          condition：指定符合条件情况下才缓存:condition ="#id>0"
     *          unless:否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果再判断
     *              unless = "#result ==null"
     *          syn:是否使用异步模式
     *
     * 原理：1.自动配置类：CacheAutoConfiguration
     *        2.缓存的配置类：
     *        3.哪个默认配置类生效：SimpleCacheConfiguration
     *        4.给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *        5.可以获取和创建ConcurrentMapCache类型的缓存组件：他的作用将数据保存在ConcurrentMap中
     *
     * 运行流程：
     *    @Cacheable
     *     1：方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取
     *     2：去Cache中查找缓存的内容，使用一个key，默认是方法的参数
     *     3：没有查到缓存调用目标方法
     *     4：将目标方法返回的结果，放进缓存中
     *
     *
     * 核心：1.使用CacheManager【ConcurrentMapCacheManager】按照名字得到【ConcurrentMapCache】组件
     *       2.key使keyGenerator生成的，默认是SimpleKeyGenerator
     */
    @Cacheable(cacheNames = {"emp"})//自定义key="#root.methodName+'['+#id#']'"
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee employee=employeeMapper.getEmpById(id);
        return employee;
    }

    /**
     * @CachePut:既调用方法，又更新缓存数据
     *   修改数据库的某个数据，同时更新缓存
     *运行时机：1.小调用目标方法 2.将目标方法的结果缓存起来
     */
    @CachePut(cacheNames = "emp",key = "#result.id")
    public  Employee updateEmp(Employee employee){
        System.out.println("updateEmp"+employee);
        employeeMapper.updateEmp(employee);
        return  employee;
    }

    /**
     * @CacheEvict:缓存清除
     * key:指定要清除的数据
     *allEntries = true,指定清除这个缓存中所有的数据
     * beforeInvocation =false:缓存的清除是否在方法之前执行
     *      默认代表是在方法执行之后执行，如果出现异常缓存不会清除
     * beforeInvocation =true:
     *      代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(cacheNames = "emp",key = "#id" )
    public  void deleteEmp(Integer id){
        System.out.println("deleteEmp"+id);
        //employeeMapper.deleteEmpById(id);
    }
    /**
     * @Caching:定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public  Employee getEmpByLasetName(String lastName){
        return  employeeMapper.getEmpByLasetName(lastName);

    }
}

package com.cn.bee.cache.service;

import com.cn.bee.cache.bean.Department;
import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.mapper.DepartmentMapper;
import com.cn.bee.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by mervin on 2018/6/25.
 */
@CacheConfig(cacheNames="dept",cacheManager = "deptCacheManager") //抽取缓存的公共配置
@Service
public class DeptService
{
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    CacheManager deptCacheManager;

    /**
     * 缓存的数据能存入redis
     * 第二次从缓存中查询就不能反序列化回来；
     * 存的是dept的josn数据；CacheManager默认使用RedisTemplate<Object,Employee> 操作redis
     * @param id
     * @return
     */
  /*  @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门："+id);
      return  departmentMapper.getDeptById(id);
    }*/
   //使用缓存管理器得到缓存，进行api调用
    public Department getDeptById(Integer id){
        System.out.println("查询部门："+id);
        Department department=departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache dept= deptCacheManager.getCache("dept");
        dept.put("dept:1",department);
        return  department;
    }
}

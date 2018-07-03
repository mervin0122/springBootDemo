package com.cn.bee.cache.controller;

import com.cn.bee.cache.bean.Department;
import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.service.DeptService;
import com.cn.bee.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mervin on 2018/6/25.
 */
@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id){
        return  deptService.getDeptById(id);
    }


}

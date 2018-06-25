package com.cn.bee.cache.service;

import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mervin on 2018/6/25.
 */
@Service
public class EmployeeService
{
    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmp(Integer id){
        Employee employee=employeeMapper.getEmpById(id);
        return employee;
    }
}

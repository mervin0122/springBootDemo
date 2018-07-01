package com.cn.bee.cache.controller;

import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mervin on 2018/6/25.
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return  employeeService.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee update(Employee employee){
        return   employeeService.updateEmp(employee);
    }
    @GetMapping("/delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }
    @GetMapping("/emp/lastname/{lastname}")
    public Employee getEmpByLasetName(@PathVariable("lastname") String lastName){
        return employeeService.getEmpByLasetName(lastName);
    }
}

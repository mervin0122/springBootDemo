package com.cn.bee.cache;

import com.cn.bee.cache.bean.Employee;
import com.cn.bee.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

	@Autowired
	EmployeeMapper empolyeeMapper;

	@Test
	public void contextLoads()
	{
		Employee empByid=empolyeeMapper.getEmpById(1);
		System.out.println(empByid);
	}

}

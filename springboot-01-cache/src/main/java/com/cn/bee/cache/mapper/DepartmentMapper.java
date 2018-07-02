package com.cn.bee.cache.mapper;

import com.cn.bee.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mervin on 2018/6/25.
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    public Department getDeptById(Integer id);
}

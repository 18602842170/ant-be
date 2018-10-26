package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.entity.Department;

public interface DepartmentMapper {
	public List<Department> searchDepartment(@Param("pId") Long pId, @Param("deptName") String deptName);
}

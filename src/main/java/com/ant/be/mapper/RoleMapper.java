package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.RoleDto;


public interface RoleMapper{
//	public int addRole(@Param("id") Integer id,@Param("name") String name,@Param("num") Integer num,@Param("permissionList") String permissionList);
	
	public List<RoleDto> searchRole(@Param("id") Long id,@Param("name") String name);
}

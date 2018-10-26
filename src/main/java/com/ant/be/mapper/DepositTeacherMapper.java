package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.DepositTeacherDto;
/**
 * 托管查询mapper
 * 
 * @author ouyangzidou
 *
 */
public interface DepositTeacherMapper {

	public List<DepositTeacherDto> searchDepositTeacherList(
			@Param("teacherName") String teacherName,
			@Param("classRoomName") String classRoomName,
			@Param("depositId") Long depositId);
}
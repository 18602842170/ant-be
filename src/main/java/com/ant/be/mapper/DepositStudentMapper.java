package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.DepositStudentDto;
/**
 * 托管查询mapper
 * 
 * @author ouyangzidou
 *
 */
public interface DepositStudentMapper {

	public List<DepositStudentDto> searchDepositStudentList(
			@Param("studentUserId") Long studentUserId,
			@Param("studentName") String studentName,
			@Param("classRoomName") String classRoomName,
			@Param("foodFlag") String foodFlag,
			@Param("depositId") Long depositId);

	public List<DepositStudentDto> searchStudentByTecherId(
			@Param("techerId") Long techerId);

}
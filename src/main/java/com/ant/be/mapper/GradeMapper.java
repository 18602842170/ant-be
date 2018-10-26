package com.ant.be.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.GradeDto;
/**
 * 班级查询mapper
 * @author xujianxia
 *
 */
public interface GradeMapper {

	// mapper里的这个方法用于向xml里的sql传值，这里定义了参数name，和参数teacherCd，在xml的sql里对应同名的条件
	public List<GradeDto> searchGrade(@Param("searchYear") int searchYear,
			@Param("searchMonth") int searchMonth,
			@Param("searchDay") int searchDay,@Param("distiguishId") Long distiguishId);

	public List<GradeDto> searchGradeById(@Param("id") Long id);
}
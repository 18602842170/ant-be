package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseDto;
import com.ant.be.dto.TeacherDto;
/**
 * 教师查询mapper
 * @author ouyangzidou
 *
 */
public interface TeacherMapper {

	// mapper里的这个方法用于向xml里的sql传值，这里定义了参数name，和参数teacherCd，在xml的sql里对应同名的条件
	public List<TeacherDto> searchTeachers(@Param("teacherName") String name,@Param("teacherCd") String teacherCd,@Param("teacherLevel") String teacherLevel,@Param("userId") Long userId);
	
	// 获取教师级别下拉框
	public List<TeacherDto> searchTeacherLevelList();

	public int searchTeacherCount();
}
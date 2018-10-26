package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.StudentDto;
/**
 * 学员查询mapper
 * @author xujianxia
 *
 */
public interface StudentMapper {

	// mapper里的这个方法用于向xml里的sql传值，这里定义了参数name，和参数teacherCd，在xml的sql里对应同名的条件
	public List<StudentDto> searchStudents(@Param("studentName") String studentName,@Param("studentSchool") String studentSchool,@Param("userId") Long userId);

	public List<StudentDto> searchStudentById(@Param("id") Long id);
}
package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseTableDto;

public interface CourseTableMapper {
	
	public List<CourseTableDto> searchCourseTable(@Param("gradeId") Long courseId);


}

package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseDto;

public interface CourseMapper {

	public List<CourseDto> searchCourse(@Param("id") Long id, @Param("name") String name,
			@Param("fine") Boolean fine,@Param("gradeId") Long gradeId,
			@Param("subjectId") Long subjectId,@Param("searchCourseDetailCount") String searchCourseDetailCount,
			@Param("searchCourseReserveCount") String searchCourseReserveCount);
	
	public List<CourseDto> searchPersonalCourse(@Param("userId") Long userId);

}

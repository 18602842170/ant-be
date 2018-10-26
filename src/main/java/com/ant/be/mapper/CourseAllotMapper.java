package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseAllotDto;

public interface CourseAllotMapper {

	public List<CourseAllotDto> searchCourseAllot(@Param("userId") Long userId, @Param("courseId") Long courseId,@Param("userType") String userType);

}

package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseReserveDto;

public interface CourseReserveMapper {
	
	public List<CourseReserveDto> searchCourseReserve(@Param("id") Long id, @Param("courseId") Long courseId, @Param("reserveCode") String reserveCode, @Param("phoneNumber") String phoneNumber);


}

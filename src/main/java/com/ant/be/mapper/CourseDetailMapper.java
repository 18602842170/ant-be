package com.ant.be.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.CourseDetailDto;

public interface CourseDetailMapper {
    
    public List<CourseDetailDto> searchCourseDetail(@Param("id") Long id, @Param("courseId") Long courseId, @Param("courseDay") Date courseDay, @Param("userId") Long userId, @Param("searchYear") int searchYear, @Param("searchMonth") int searchMonth, @Param("searchDay") int searchDay, @Param("courseDayStr") String courseDayStr, @Param("isRollCall") Boolean isRollCall, @Param("isteacher") Long isteacher, @Param("isstudent") Long isstudent, @Param("techerId") Long techerId);
    
}

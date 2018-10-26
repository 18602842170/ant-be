package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.UserDto;

public interface UserMapper {
    
    public List<UserDto> searchUsers(@Param("name") String name, @Param("roleId") Long roleId, @Param("deptId") Long deptId, @Param("phoneNumber") String phoneNumber);
    
    public int searchUsersCount();
    
    public List<UserDto> searchTecherByCourseDetailId(@Param("courseDetailId") Long courseDetailId);
    
    public List<UserDto> searchStudentByCourseId(@Param("courseId") Long CourseId);
}

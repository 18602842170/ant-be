package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.ClassroomDto;
/**
 * 教室查询mapper
 * @author xujianxia
 *
 */
public interface ClassroomMapper {

	// mapper里的这个方法用于向xml里的sql传值，这里定义了参数name，和参数teacherCd，在xml的sql里对应同名的条件
	public List<ClassroomDto> searchClassrooms(@Param("classroomName") String classroomName,@Param("classroomAddress") String classroomAddress,@Param("classroomPeople") Long classroomPeople);

	public List<ClassroomDto> searchClassroomById(@Param("id") Long id);
}
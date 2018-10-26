package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ant.be.dto.RollCallDto;

public interface RollCallMapper {

	public List<RollCallDto> searchRollCall(@Param("userId") Long userId, 
			@Param("courseId") Long courseId,
			@Param("searchYear") int searchYear,
			@Param("searchMonth") int searchMonth,
			@Param("searchDay") int searchDay,
			@Param("courseDetailId") Long courseDetailId,
			@Param("id") Long id
			);
			

}

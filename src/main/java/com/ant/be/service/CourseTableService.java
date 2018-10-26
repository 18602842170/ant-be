package com.ant.be.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.CourseTableDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ant.be.mapper.CourseTableMapper;

@Component
public class CourseTableService {

	@Autowired
	private CourseTableMapper courseTableMapper;
	
	/**
	 * 检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(CourseTableDto courseTable) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(courseTable.getPageNum(), courseTable.getPageSize());
		
		if(courseTable.getGradeId() == 0){
			courseTable.setGradeId(null);
		}

		List<CourseTableDto> courses = courseTableMapper.searchCourseTable(courseTable.getGradeId());
		
		PageInfo<CourseTableDto> p = new PageInfo<CourseTableDto>(courses);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());
		map.put("results", courses);
		map.put("next", null);
		map.put("previous", null);

		return map;
	}
	

}

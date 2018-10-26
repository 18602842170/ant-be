package com.ant.be.admin;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.CourseReserveDto;
import com.ant.be.dto.CourseTableDto;
import com.ant.be.service.CourseReserveService;
import com.ant.be.service.CourseTableService;

@RestController
@RequestMapping("/course_table")
public class CourseTableController {

	@Autowired
	private CourseTableService courseTableService;


	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody CourseTableDto courseDto) throws ClassNotFoundException {
		return courseTableService.search(courseDto);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute CourseTableDto courseDto) throws ClassNotFoundException {
		Map<String, Object> map = courseTableService.search(courseDto);
		return map;
	}

}

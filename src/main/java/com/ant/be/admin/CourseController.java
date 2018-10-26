package com.ant.be.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.CourseDetailDto;
import com.ant.be.dto.CourseDto;
import com.ant.be.entity.Course;
import com.ant.be.service.CourseAllotService;
import com.ant.be.service.CourseDetailService;
import com.ant.be.service.CourseService;
import com.ant.be.service.DistinguishService;
import com.ant.be.service.TeacherService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseDetailService courseDetailService;

	/**
	 * 单例查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable("id") Long id) {
		return courseService.searchSingle(id);
	}
	
	
	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody CourseDto courseDto) throws ClassNotFoundException {
		return courseService.search(courseDto);
	}
	
	
	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute CourseDto courseDto) throws ClassNotFoundException {
		Map<String, Object> map = courseService.search(courseDto);
		return map;
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody CourseDto course) {
		return courseService.update(id, course, "update");
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute CourseDto course) {
		return courseService.update(id, course, "update");
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Course course){
		return courseService.creat(course);
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	@RequestMapping(value = "/create",method = RequestMethod.GET)
	public Object wechatCreat(@ModelAttribute CourseDto course){
		return courseService.creat(course);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return courseService.update(id, null, "delete");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object wechatDelete(@PathVariable("id") Long id) {
		return courseService.update(id, null, "delete");
	}
	/**
	 * 新建课时
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/saveCourseClass",method = RequestMethod.POST)
	public Object saveCourseClass(@RequestBody CourseDetailDto courseDetailDto) {
		return courseDetailService.creatClass(courseDetailDto);
	}
	
}

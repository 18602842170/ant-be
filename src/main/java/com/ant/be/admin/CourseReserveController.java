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

import com.ant.be.dto.CourseReserveDto;
import com.ant.be.service.CourseReserveService;

@RestController
@RequestMapping("/course_reserve")
public class CourseReserveController {

	@Autowired
	private CourseReserveService courseReserveService;

	/**
	 * 单例查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable("id") Long id) {
		return courseReserveService.searchSingle(id);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody CourseReserveDto courseDto) throws ClassNotFoundException {
		return courseReserveService.search(courseDto);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute CourseReserveDto courseDto) throws ClassNotFoundException {
		Map<String, Object> map = courseReserveService.search(courseDto);
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
	public Object update(@PathVariable("id") Long id, @RequestBody CourseReserveDto course) {
		return courseReserveService.update(id, course, "update");
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute CourseReserveDto course) {
		return courseReserveService.update(id, course, "update");
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
	public Object creat(@RequestBody CourseReserveDto course){
		return courseReserveService.creat(course);
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
	public Object wechatCreat(@ModelAttribute CourseReserveDto course){
		return courseReserveService.creat(course);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return courseReserveService.update(id, null, "delete");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object wechatDelete(@PathVariable("id") Long id) {
		return courseReserveService.update(id, null, "delete");
	}
}

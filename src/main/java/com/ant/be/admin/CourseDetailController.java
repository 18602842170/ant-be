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
import com.ant.be.service.CourseDetailService;

@RestController
@RequestMapping("/course_detail")
public class CourseDetailController {

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
		return courseDetailService.searchSingle(id);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody CourseDetailDto courseDetailDto) throws ClassNotFoundException {
		return courseDetailService.search(courseDetailDto);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute CourseDetailDto courseDetailDto) throws ClassNotFoundException {
		Map<String, Object> map = courseDetailService.search(courseDetailDto);
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
	public Object update(@PathVariable("id") Long id, @RequestBody CourseDetailDto courseDetailDto) {
		return courseDetailService.update(id, courseDetailDto, "update");
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute CourseDetailDto courseDetailDto) {
		return courseDetailService.update(id, courseDetailDto, "update");
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
	public Object creat(@RequestBody CourseDetailDto courseDetailDto){
		return courseDetailService.creat(courseDetailDto);
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
	public Object wechatCreat(@ModelAttribute CourseDetailDto courseDetailDto){
		return courseDetailService.creat(courseDetailDto);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return courseDetailService.update(id, null, "delete");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object wechatDelete(@PathVariable("id") Long id) {
		return courseDetailService.update(id, null, "delete");
	}
	
	/**
	 * 根据日期和课程id删除具体课时信息
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/removeByDate", method = RequestMethod.POST)
	public Object removeByDate(@RequestBody CourseDetailDto courseDetailDto){
		return courseDetailService.removeByDate(courseDetailDto);
	}

}

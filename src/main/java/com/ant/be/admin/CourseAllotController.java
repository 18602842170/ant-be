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

import com.ant.be.dto.CourseAllotDto;
import com.ant.be.entity.CourseAllot;
import com.ant.be.service.CourseAllotService;

@RestController
@RequestMapping("/course_allot")
public class CourseAllotController {

	@Autowired
	private CourseAllotService courseAllotService;

	/**
	 * 单例查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable("id") Long id) {
		return courseAllotService.searchSingle(id);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody CourseAllotDto courseAllotDto) throws ClassNotFoundException {
		return courseAllotService.search(courseAllotDto);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute CourseAllotDto courseAllotDto) throws ClassNotFoundException {
		Map<String, Object> map = courseAllotService.search(courseAllotDto);
		return map;
	}
	
	
	@Transactional
	@RequestMapping(value = "/updatesCourseAllot",method = RequestMethod.POST)
	public Object updatesCourseAllot(@RequestBody CourseAllot courseAllot) {
		
		
		return courseAllotService.updateByCourseId(courseAllot);
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody CourseAllotDto courseAllotDto) {
		return courseAllotService.update(id, courseAllotDto, "update");
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute CourseAllotDto courseAllotDto) {
		return courseAllotService.update(id, courseAllotDto, "update");
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
	public Object creat(@RequestBody CourseAllot courseAllot){
		return courseAllotService.creat(courseAllot);
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
	public Object wechatCreat(@ModelAttribute CourseAllotDto courseAllotDto){
		return courseAllotService.creat(courseAllotDto);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return courseAllotService.update(id, null, "delete");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object wechatDelete(@PathVariable("id") Long id) {
		return courseAllotService.update(id, null, "delete");
	}

}

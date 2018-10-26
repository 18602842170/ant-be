package com.ant.be.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ant.be.dto.TeacherDto;
import com.ant.be.entity.Teacher;
import com.ant.be.service.TeacherService;
/**
 * 教师模块
 * 
 * @author ouyangzidou
 *
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 教师信息页面初期查询
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object searchTeacherList(@RequestBody TeacherDto dto) throws Exception {
		// 调用service里的方法检索数据
		return teacherService.search(dto);
	}
	
	/**
	 * 教师信息页面初期查询微信用
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatSearchTeacherList(@ModelAttribute TeacherDto dto) throws Exception {
		return teacherService.search(dto);
	}
	
	
	/**
	 * 编辑教师
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public Object updateTeacher(@PathVariable("id") Long id,@RequestBody TeacherDto teacher) throws Exception {
		return teacherService.updateTeacher(id,teacher);
	}
	
	/**
	 * 根据id查找指定教师
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Object findTeacherById(@PathVariable("id") Long id) throws Exception {
		return teacherService.findTeacherById(id);
	}
	
	/**
	 * 创建教师
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Teacher dto) throws Exception {
		return teacherService.creat(dto);
	}
}

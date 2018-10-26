package com.ant.be.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.dto.StudentDto;
import com.ant.be.entity.Student;
import com.ant.be.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 学员
 * @author xujianxia
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * 教师信息页面初期查询
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object searchStudentList(@RequestBody StudentDto dto) throws Exception {

		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		
		// 调用service里的方法检索数据
		List<StudentDto> studentList = studentService.search(dto);
		
		PageInfo<StudentDto> p = new PageInfo<StudentDto>(studentList);
		
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());	// 总数
		map.put("results", p.getList()); // 结果集	
		map.put("next", null); 
		map.put("previous", null);
		return map;
	}
	
	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
//	@RequestMapping(value = "/findCourseInfor", method = RequestMethod.GET)
//	public Object findCourseInfor(@RequestParam("courseId") String courseId,@RequestParam("courseDate") String courseDate ) {
//		List<StudentCourseDetailDto> studentList = studentService.findCourseById(courseId,courseDate);
//		PageInfo<StudentCourseDetailDto> p = new PageInfo<StudentCourseDetailDto>(studentList);
//		
//		// 准备返回的map
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("count", p.getTotal());	// 总数
//		map.put("results", p.getList()); // 结果集	
//		map.put("next", null); 
//		map.put("previous", null);
//		return map;
//	}
	
	/**
	 * 新建学员
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Student student) throws Exception {
		return studentService.creat(student);
	}
	
	/**
	 * 更新学员
	 * 
	 * @param student
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody Student student){
		return studentService.update(id, student, Const.update);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return studentService.update(id, null, Const.delete);
	}
	
	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable("id") Long id) {
		return studentService.findStudentById(id);
	}
	
}

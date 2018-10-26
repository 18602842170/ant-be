package com.ant.be.admin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.DepositTeacherDto;
import com.ant.be.service.DepositTeacherService;
/**
 * 托管教师模块
 * 
 * @author ouyangzidou
 *
 */
@RestController
@RequestMapping("/depositTeacher")
public class DepositTeacherController {
	
	@Autowired
	private DepositTeacherService depositTeacherService;
	
	/**
	 * 查询托管信息一览
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object searchDepositList(@RequestBody DepositTeacherDto dto) throws Exception {
		// 调用service里的方法检索数据
		return depositTeacherService.search(dto);
	}
	
	/**
	 * 微信用查看托管信息一览
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatSearchDepositList(@ModelAttribute DepositTeacherDto dto) throws Exception {
		return depositTeacherService.search(dto);
	}
	
	
	/**
	 * 编辑托管信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public Object updateDeposit(@PathVariable("id") Long id,@RequestBody DepositTeacherDto teacher) throws Exception {
		return depositTeacherService.updateDeposit(id,teacher);
	}
	
	/**
	 * 根据id查找托管信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Object findDepositById(@PathVariable("id") Long id) throws Exception {
		return depositTeacherService.findDepositById(id);
	}
	
	/**
	 * 创建托管信息
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody DepositTeacherDto dto) throws Exception {
		return depositTeacherService.creat(dto);
	}
	
	/**
	 * 删除托管信息
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return depositTeacherService.delete(id);
	}
}

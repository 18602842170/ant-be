package com.ant.be.admin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.DepositStudentDto;
import com.ant.be.service.DepositStudentService;
/**
 * 托管学员模块
 * 
 * @author ouyangzidou
 *
 */
@RestController
@RequestMapping("/depositStudent")
public class DepositStudentController {
	
	@Autowired
	private DepositStudentService depositStudentService;
	
	/**
	 * 查询托管信息一览
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object searchDepositList(@RequestBody DepositStudentDto dto) throws Exception {
		// 调用service里的方法检索数据
		return depositStudentService.search(dto);
	}
	
	/**
	 * 微信用查看托管信息一览
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatSearchDepositList(@ModelAttribute DepositStudentDto dto) throws Exception {
		return depositStudentService.search(dto);
	}
	
	
	/**
	 * 编辑托管信息
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public Object updateDeposit(@PathVariable("id") Long id,@RequestBody DepositStudentDto dto) throws Exception {
		return depositStudentService.updateDeposit(id,dto);
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
		return depositStudentService.findDepositById(id);
	}
	
	/**
	 * 创建托管信息
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody DepositStudentDto dto) throws Exception {
		return depositStudentService.creat(dto);
	}
	
	/**
	 * 删除托管信息
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return depositStudentService.delete(id);
	}
}

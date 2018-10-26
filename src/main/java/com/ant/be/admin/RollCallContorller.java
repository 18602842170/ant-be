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

import com.ant.be.dto.RollCallDto;
import com.ant.be.service.RollCallService;

@RestController
@RequestMapping("/roll_call")
public class RollCallContorller {

	@Autowired
	private RollCallService rollCallService;

	/**
	 * 单例查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable("id") Long id) {
		return rollCallService.searchSingle(id);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody RollCallDto rollCallDto) throws ClassNotFoundException {
		return rollCallService.search(rollCallDto);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute RollCallDto rollCallDto) throws ClassNotFoundException {
		Map<String, Object> map = rollCallService.search(rollCallDto);
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
	public Object update(@PathVariable("id") Long id, @RequestBody RollCallDto rollCallDto) {
		return rollCallService.update(id, rollCallDto, "update");
	}

	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute RollCallDto rollCallDto) {
		return rollCallService.update(id, rollCallDto, "update");
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
	public Object creat(@RequestBody RollCallDto rollCallDto){
		if(rollCallDto.getSaveList() != null && !rollCallDto.getSaveList().equals("") ) {
			rollCallService.creatBySaveList(rollCallDto);
			return "success";
		}else {
			return rollCallService.creat(rollCallDto);
		}
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
	public Object wechatCreat(@ModelAttribute RollCallDto rollCallDto){
		if(rollCallDto.getSaveList() != null && !rollCallDto.getSaveList().equals("") ) {
			rollCallService.creatBySaveList(rollCallDto);
			return "success";
		}else {
			return rollCallService.creat(rollCallDto);
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return rollCallService.update(id, null, "delete");
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Object wechatDelete(@PathVariable("id") Long id) {
		return rollCallService.update(id, null, "delete");
	}


}

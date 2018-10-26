package com.ant.be.admin;

import java.lang.reflect.InvocationTargetException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.dto.DistinguishDto;
import com.ant.be.service.DistinguishService;
/**
 * 设定值画面
 * @author chenzhuoqi
 *
 */
@RestController
@RequestMapping("/distinguish")
public class DistinguishController {

	@Autowired
	private DistinguishService distinguishService;

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody DistinguishDto constDto) throws ClassNotFoundException {
		return distinguishService.search(constDto);
	}
	
	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute DistinguishDto constDto) throws ClassNotFoundException {
		return distinguishService.search(constDto);
	}

	/**
	 * 带参查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getUser(@PathVariable("id") Long id) {
		return distinguishService.searchSingle(id);
	}

	/**
	 * 编辑
	 * @param id
	 * @param constDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object save(@PathVariable("id") Long id, @RequestBody DistinguishDto constDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return distinguishService.update(id, constDto, Const.update);
	}

	/**
	 * 新建
	 * @param constDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody DistinguishDto constDto)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return distinguishService.creat(constDto);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return distinguishService.update(id, null, Const.delete);
	}

}

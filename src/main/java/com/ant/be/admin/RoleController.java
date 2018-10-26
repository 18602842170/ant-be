package com.ant.be.admin;

import java.lang.reflect.InvocationTargetException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.dto.RoleDto;
import com.ant.be.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody RoleDto role) throws ClassNotFoundException {
		return roleService.search(role);
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
		return roleService.searchSingle(id);
	}

	/**
	 * 编辑roles
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object save(@PathVariable("id") Long id, @RequestBody RoleDto roles) {
		return roleService.update(id, roles, Const.update);
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
	public Object creat(@RequestBody RoleDto role)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return roleService.creat(role);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return roleService.update(id, null, Const.delete);
	}

}

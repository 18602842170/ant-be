package com.ant.be.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.entity.Department;
import com.ant.be.form.DepartmentForm;
import com.ant.be.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	/**
	 * 带参查询
	 * 
	 * @param user
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody DepartmentForm dep) throws ClassNotFoundException {
		return departmentService.searchDepartment(dep);
	}

	/**
	 * 新建
	 * 
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Department department) {
		return departmentService.creat(department);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody Department department) {
		return departmentService.update(id, department, Const.update);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return departmentService.update(id, null, Const.delete);
	}

}

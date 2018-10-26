package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.entity.Department;
import com.ant.be.entity.Users;
import com.ant.be.form.DepartmentForm;
import com.ant.be.mapper.DepartmentMapper;
import com.ant.be.mapper.UserMapper;
import com.ant.be.repository.DepartmentRepository;
import com.ant.be.repository.UserRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;

@Component
public class DepartmentService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 查询部门
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public Object searchDepartment(DepartmentForm form) {
		List<Department> dept = null;
		dept = departmentMapper.searchDepartment(form.getpId(), form.getDeptName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", dept.size());
		map.put("results", dept);
		map.put("next", null);
		map.put("previous", null);
		return map;
	}

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Users> searchSingle(Long id) {
		Optional<Users> user = userRepository.findById(id);
		return user;
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public Department creat(Department department) {
		department.setDeleteFlg(false);
		if (null == department.pId) {
			department.pId = (long) 0;
		}
		department.setCreatDate(DateUtil.format(new Date()));
		department.setUpdateDate(DateUtil.format(new Date()));
		return departmentRepository.save(department);
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public Department update(Long id, Department department, String flg) {
		Optional<Department> departments = departmentRepository.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(department, departments.get());
			departments.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			departments.get().setDeleteFlg(true);
			List<Department> deptList = departmentMapper.searchDepartment(departments.get().getId(), null);
			// 删除子节点
			if (null != deptList && deptList.size() > 0) {
				for(Department dept : deptList) {
					dept.setDeleteFlg(true);
					dept.setUpdateDate(DateUtil.format(new Date()));
					departmentRepository.save(dept);
				}
			}
		}
		departments.get().setUpdateDate(DateUtil.format(new Date()));
		return departmentRepository.save(departments.get());
	}

}

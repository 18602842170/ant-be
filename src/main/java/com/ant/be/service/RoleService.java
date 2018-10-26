package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.RoleDto;
import com.ant.be.entity.Role;
import com.ant.be.mapper.RoleMapper;
import com.ant.be.repository.RoleRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.ToolUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 检索
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(RoleDto role) {
		PageHelper.startPage(role.getPageNum(), role.getPageSize());
		if (!("").equals(role.getName()) && null != role.getName()) {
			role.setName("%" + role.getName() + "%");
		}
		List<RoleDto> roles = roleMapper.searchRole(role.getId(), role.getName());
		if (null != roles && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				if (null != roles.get(i).getId()) {
					String str = roles.get(i).getPermissionList().replace("\\", "");
					roles.get(i).setName(roles.get(i).getName());
					roles.get(i).setPermissionMap(ToolUtil.toJsonObject(str.substring(1, str.length() - 1)));
				}

			}
		}
		PageInfo<RoleDto> p = new PageInfo<RoleDto>(roles);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());
		if (null != role.getId()) {
			map.put("results", roles);
		} else {
			map.put("results", p.getList());
		}
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
	public RoleDto searchSingle(Long id) {
		List<RoleDto> roles = roleMapper.searchRole(id, null);
		if (null != roles && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				if (null != roles.get(i).getId()) {
					String str = roles.get(i).getPermissionList().replace("\\", "");
					roles.get(i).setName(roles.get(i).getName());
					roles.get(i).setPermissionMap(ToolUtil.toJsonObject(str.substring(1, str.length() - 1)));
				}

			}
		}
		return roles.get(0);
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public Role creat(RoleDto role) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Role roles = new Role();
		ToolUtil.copyProperties(role, roles);
		roles.setPermissionList(ToolUtil.objectToString(role.getPermissionMap()));
		roles.setDeleteFlg(false);
		roles.setCreatDate(DateUtil.format(new Date()));
		roles.setUpdateDate(DateUtil.format(new Date()));
		return roleRepository.save(roles);

	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public Role update(Long id, RoleDto roles, String flg) {
		Optional<Role> role = roleRepository.findById(id);
		if (flg.equals("update")) {
			role.get().setName(roles.getName());
			role.get().setPermissionList(ToolUtil.objectToString(roles.getPermissionMap()));
		} else if (flg.equals("delete")) {
			role.get().setDeleteFlg(true);
		}
		role.get().setUpdateDate(DateUtil.format(new Date()));
		return roleRepository.save(role.get());
	}

}

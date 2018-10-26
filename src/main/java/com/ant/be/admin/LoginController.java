package com.ant.be.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.UserDto;
import com.ant.be.entity.Role;
import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.repository.RoleRepository;
import com.ant.be.repository.UserRepository;
import com.ant.be.shiro.UserToken;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.ShiroKit;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * 登录方法
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public String ajaxLogin(@RequestBody Users user) {
		
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("name", user.name, true));
		criteria.add(Restrictions.eq("deleteFlg", false, true));
		List<Users> users = userRepository.findAll(criteria);
		JSONObject jsonObject = new JSONObject();
		
		if(null != users && users.size() > 0) {
			Subject subject = SecurityUtils.getSubject();
			UserToken token = new UserToken(users.get(0).getName(), user.password, null);
			token.setRememberMe(true);
			try {
				// delSub.login(token);
				subject.login(token);
				// 保存sessionId
				
				if (null != users && users.size() > 0) {
					// 更新session
					users.get(0).setSessionId(subject.getSession().getId().toString());
					Users userEntity = userRepository.save(users.get(0));
					UserDto userDto = new UserDto();
					userDto.setId(userEntity.getId());
					userDto.setName(userEntity.getName());
					userDto.setNikeName(userEntity.getNikeName());
					if (null != userEntity.getRoleId()) {

						Optional<Role> roles = roleRepository.findById(userEntity.getRoleId());
						JSONObject jsonobject = JSONObject.fromObject(roles.get().getPermissionList());
						Map<String, String> per = (Map<String, String>) JSONObject.toBean(jsonobject, Map.class);
						userDto.setPermissionMap(per);
					}
					jsonObject.put("token", subject.getSession().getId());
					jsonObject.put("user", userDto);
					jsonObject.put("msg", "success");

				}else {
					jsonObject.put("msg", "nameError");
				}
			} catch (IncorrectCredentialsException e) {
				jsonObject.put("msg", "passwordError");
				return jsonObject.toString();
			} catch (LockedAccountException e) {
				jsonObject.put("msg", "userDelete");
				return jsonObject.toString();
			} catch (AuthenticationException e) {
				jsonObject.put("msg", "nameError");
				return jsonObject.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			jsonObject.put("msg", "userDelete");
		}
		

		return jsonObject.toString();
	}

	/**
	 * 查询重复username
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getRepeatUserName")
	public Object getRepeatUserName(@RequestBody Users user) {
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("name", user.getName(), true));
		List<Users> users = userRepository.findAll(criteria);
		return users.size();
	}

	/**
	 * 登录后返回页面权限信息 permissions:页面权限集合 userName:用户名 userId:用户id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUserBySessionId")
	public Object getUserBySessionId(@RequestBody Users user) {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().getId();
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("sessionId", user.getSessionId(), true));
		List<Users> users = userRepository.findAll(criteria);
		if (null != users && users.size() > 0) {

			Users userEntity = users.get(0);
			UserDto userDto = new UserDto();
			userDto.setId(userEntity.getId());
			userDto.setName(userEntity.getName());
			Role roles = roleRepository.getOne(userEntity.getRoleId());
			JSONObject jsonobject = JSONObject.fromObject(roles.getPermissionList());
			Map<String, String> per = (Map<String, String>) JSONObject.toBean(jsonobject, Map.class);
			userDto.setPermissionMap(per);
			return userDto;
		} else {
			return null;
		}
	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register")
	public Object register(@RequestBody Users user) {
		JSONObject jsonObject = new JSONObject();
		// 保存sessionId
		user.setPassword(ShiroKit.toSimpleHash(user.getPassword(), user.getName()));
		user.setSalt(ShiroKit.converToSalt(user.getName()));
		user.setCreatDate(DateUtil.format(new Date()));
		user.setUpdateDate(DateUtil.format(new Date()));
		user.setDeleteFlg(false);
		user = userRepository.save(user);
		user.password = null;
		return user;
	}

	/**
	 * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/unauth")
	@ResponseBody
	public Object unauth() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1000000");
		map.put("msg", "未登录");
		return map;
	}
}

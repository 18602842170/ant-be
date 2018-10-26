package com.ant.be.shiro.config;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.repository.RoleRepository;
import com.ant.be.repository.UserRepository;

public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	private UserRepository userRepository;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// ShiroUser userInfo = (ShiroUser) principals.getPrimaryPrincipal();
		// Criteria<Users> criteria = new Criteria<>();
		// criteria.add(Restrictions.eq("name", "AAA", true));
		// List<Users> user=userRepository.findAll(criteria);
		// if(null != user && user.size() > 0) {
		//
		// Criteria<Role> cr = new Criteria<>();
		// cr.add(Restrictions.eq("id", user.get(0).getId(), true));
		// List<Role> role=roleRepository.findAll(cr);
		// if(null != role && role.size()>0) {
		// // 基于role的权限信息
		// authorizationInfo.addRole(role.get(0).getName());
		// // 基于permission的权限信息
		// authorizationInfo.addStringPermission(role.get(0).getPermissionList());
		// }
		// }

		return authorizationInfo;

	}

	/**
	 * 登录认证
	 */
	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
//		UserToken userToken = (UserToken) token;

		// System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("name", username, false));
		criteria.add(Restrictions.eq("deleteFlg", false, true));
		List<Users> user = userRepository.findAll(criteria);
		Object password=new Object();
		// System.out.println("----->>userInfo="+userInfo);
		if (0 == user.size()) {
			return null;
		}
//		if (Const.BYPHONE.equals(userToken.getLoginType())) {
//			password=user.get(0).wechatOpenId;
//		}else if(null == userToken.getLoginType()) {
//		}
		password=user.get(0).password;
		// if (userInfo.getState() == 1) { // 账户冻结
		// throw new LockedAccountException();
		// }
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.get(0), // 用户名
				password, // 密码
				ByteSource.Util.bytes(username), // salt=username的salt
				getName() // realm name
		);
		return authenticationInfo;
	}
}

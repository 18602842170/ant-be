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

import com.ant.be.common.constant.Const;
import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.repository.UserRepository;
import com.ant.be.shiro.UserToken;

public class ByCodeShiroRealm extends AuthorizingRealm {
	@Resource
	private UserRepository userRepository;

    /**
     * 权限认证
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		return authorizationInfo;
		
	}

	/**
     * 登录认证
     */
	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的openId
		String username = (String) token.getPrincipal();
		UserToken userToken=(UserToken) token;
		Criteria<Users> criteria = new Criteria<>();
		if(Const.BYPHONE.equals(userToken.getLoginType())) {
			criteria.add(Restrictions.eq("id", Long.valueOf(username), true));
		}else if(Const.NOPASSWORD.equals(userToken.getLoginType())) {
			criteria.add(Restrictions.eq("wechatOpenId", username, true));
		}else if(Const.BYVERIFYCODE.equals(userToken.getLoginType())) {
			criteria.add(Restrictions.eq("name", username, true));
		}
		criteria.add(Restrictions.eq("deleteFlg", false, true));
		List<Users> user=userRepository.findAll(criteria);
		if (user.size() == 0) {
			return null;
		}else {
			
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.get(0),user.get(0).getPassword(),ByteSource.Util.bytes(user.get(0).getName()), // salt=username的salt
					getName() // realm name
					);
			return authenticationInfo;
		}
	}
}

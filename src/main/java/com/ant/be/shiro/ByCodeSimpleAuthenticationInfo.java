package com.ant.be.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class ByCodeSimpleAuthenticationInfo extends SimpleAuthenticationInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ByCodeSimpleAuthenticationInfo(Object users,String realmName) {
		this.principals =new SimplePrincipalCollection(users,realmName);
	}
}

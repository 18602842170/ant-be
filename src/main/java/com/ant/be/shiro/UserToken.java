package com.ant.be.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken{
	
	//登录类型，判断是哪种方式登录
    private String loginType;
    
    public UserToken( String username,  String password,String loginTypes) {
        super(username,password);
        this.loginType = loginTypes;
    }
    
    
    public UserToken( String username,String loginTypes) {
        super(username,"",null);
        this.loginType = loginTypes;
    }

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
    
}

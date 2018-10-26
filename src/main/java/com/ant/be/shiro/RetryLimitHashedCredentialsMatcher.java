package com.ant.be.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.ant.be.common.constant.Const;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

		UserToken noPasswordToken = (UserToken) token;
		// 如果是免密，就不需要核对密码了
		if (Const.NOPASSWORD.equals(noPasswordToken.getLoginType()) || Const.BYPHONE.equals(noPasswordToken.getLoginType())
				|| Const.BYVERIFYCODE.equals(noPasswordToken.getLoginType())) {
			return true;
		}
		return super.doCredentialsMatch(token, info);
	}

}

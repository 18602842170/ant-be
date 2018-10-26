package com.ant.be.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.ant.be.common.constant.Const;

public class MyFormAuthenticationFilterextends extends FormAuthenticationFilter {

    private String loginTypeParamName = Const.NOPASSWORD;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request
            , ServletResponse response) throws Exception {
//        String successUrl = "/index";
//        WebUtils.issueRedirect(request,response,successUrl);
        return false;
    }

    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
//        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String loginType = "";

        if(request.getParameter("loginType")!=null && !"".equals(request.getParameter("loginType").trim())){
            loginType = request.getParameter("loginType");
        }
        if(loginTypeParamName.equals(loginType)) {
        	return new UserToken(username, password,loginType);
        }else {
        	return new UserToken(username,loginType);
        }
    }

    public String getLoginTypeParamName() {
        return loginTypeParamName;
    }

    public void setLoginTypeParamName(String loginTypeParamName) {
        this.loginTypeParamName = loginTypeParamName;
    }
}

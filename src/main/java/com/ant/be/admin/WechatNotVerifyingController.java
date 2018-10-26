package com.ant.be.admin;

import java.io.UnsupportedEncodingException;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.service.WechatNotVerifyingService;

@RestController
@RequestMapping("/not_verifying")
public class WechatNotVerifyingController {
	

	@Autowired
	private WechatNotVerifyingService wechatNotVerifyingService;

	/**
	 * 手机登錄验证
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/phone_check/")
	public String phoneCheck(@PathParam(value = "phoneNumber") String phoneNumber,
			@PathParam(value = "verify") String verify, @PathParam(value = "code") String code) {
		return wechatNotVerifyingService.phoneVerifyCheck(phoneNumber, verify, code);
	}

	/**
	 * 账号密码登陆
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/wechat_login/", method = RequestMethod.GET)
	public String wechatLogin(@PathParam("name") String name,@PathParam("password") String password,@PathParam("code") String code) {
		return wechatNotVerifyingService.wechatLogin(name, password, code);
	}
	
	/**
	 * 验证码登录
	 * @param name
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value = "/verifyCode_login", method = RequestMethod.GET)
	public String wechatLogin(@PathParam("name") String name,@PathParam("verifyCode") String verifyCode) {
		return wechatNotVerifyingService.verifyCodeLogin(name, verifyCode);
	}
	
	/**
	 * 微信自动登陆
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getTokenByCode/")
	public String getTokenByCode(@PathParam("code") String code) {
		return wechatNotVerifyingService.getTokenByCode(code);
	}
	
	/**
	 * 课程预约
	 * 
	 * @param id
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	@RequestMapping(value = "/course_reserve/", method = RequestMethod.GET)
	public Object courseReserve(@PathParam("formId") String formId, 
			@PathParam("phoneNumber") String phoneNumber, 
			@PathParam("courseId") Long courseId, 
			@PathParam("code") String code) throws Exception{

		return wechatNotVerifyingService.courseReserve(formId, phoneNumber, courseId, code);
	}
	
	/**
	 * 课程预约人数
	 * 
	 * @param id
	 * @throws UnsupportedEncodingException 
	 */
	@Transactional
	@RequestMapping(value = "/course_reserve_count/", method = RequestMethod.GET)
	public Object courseReserveCount(@PathParam("id") Long id){

		return wechatNotVerifyingService.searchCourseReserveCount(id);
	}
}

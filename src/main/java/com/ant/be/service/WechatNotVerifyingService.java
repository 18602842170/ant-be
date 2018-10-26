package com.ant.be.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.common.WechatConstData;
import com.ant.be.common.constant.Const;
import com.ant.be.dto.CourseReserveDto;
import com.ant.be.entity.Course;
import com.ant.be.entity.CourseReserve;
import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.repository.UserRepository;
import com.ant.be.shiro.UserToken;
import com.ant.be.utils.DateUtil;

import net.sf.json.JSONObject;

@Component
public class WechatNotVerifyingService {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseReserveService courseReserveService;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 手机登錄验证
	 * 
	 * @param user
	 * @return
	 */
	public String phoneVerifyCheck(String phoneNumber, String verify, String code) {
		// 返回信息
		JSONObject jsonObject = new JSONObject();

		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("phoneNumber", phoneNumber, true));
		criteria.add(Restrictions.eq("verify", verify, true));
		List<Users> users = userRepository.findAll(criteria);
		// 找到用户表示验证通过，将openid session 保存
		if (users.size() > 0) {
			JSONObject rsp = null;
			try {
				// 获取微信信息
				rsp = getWechatUserInfo(code);
			} catch (Exception e) {
				jsonObject.put("msg", "timeout");
				return jsonObject.toString();
			}

			Users user = users.get(0);
			// 保存openid
			user.wechatOpenId = rsp.getString("openid");
			user.setUpdateDate(DateUtil.format(new Date()));
			userRepository.save(user);
			jsonObject.put("msg", "success");
		}
		return jsonObject.toString();
	}

	/**
	 * 账号密码验证
	 * 
	 * @param name
	 * @param password
	 * @param code
	 * @return
	 */
	public String wechatLogin(String name, String password, String code) {
		// 返回信息
		JSONObject jsonObject = new JSONObject();

		Subject subject = SecurityUtils.getSubject();
		UserToken token = new UserToken(name, password, null);
		token.setRememberMe(true);
		// 保存微信的openid
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("name", name, true));
		criteria.add(Restrictions.eq("deleteFlg", false, true));
		List<Users> users = userRepository.findAll(criteria);
		if (null != users && users.size() > 0) {
			try {
				// 通过此句代码表示账号密码验证通过
				subject.login(token);
				Users user = users.get(0);

				JSONObject rsp = null;
				try {
					// 获取微信信息
					rsp = getWechatUserInfo(code);
				} catch (Exception e) {
					jsonObject.put("msg", "timeout");
					return jsonObject.toString();
				}
				// 保存openid
				user.wechatOpenId = rsp.getString("openid");
				user.setUpdateDate(DateUtil.format(new Date()));
				userRepository.save(user);
				jsonObject.put("msg", "success");

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
		} else {
			jsonObject.put("msg", "userDelete");
		}
		return jsonObject.toString();
	}

	/**
	 * 验证码登录
	 * 
	 * @param name
	 * @param code
	 * @return
	 */
	public String verifyCodeLogin(String name, String code) {
		// 返回信息
		JSONObject jsonObject = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("name", name, true));
		criteria.add(Restrictions.eq("verifyCode", code, true));
		criteria.add(Restrictions.eq("deleteFlg", false, true));
		List<Users> users = userRepository.findAll(criteria);
		UserToken token = new UserToken(name, code, Const.BYVERIFYCODE);
		try {
			subject.login(token);
			if (users.size() > 0) {
				Users user = users.get(0);
				if (DateUtil.format(user.verifyDate).compareTo(DateUtil.format(new Date())) > 0
						|| DateUtil.format(user.verifyDate).compareTo(DateUtil.format(new Date())) == 0) {
					// 当验证码时间大于等于现在时间，有效
					// 保存WechatSessionId
					user.setWechatSessionId(subject.getSession().getId().toString());
					user.setUpdateDate(DateUtil.format(new Date()));
					userRepository.save(user);
					jsonObject.put("user", user);
					jsonObject.put("token", subject.getSession().getId());
					jsonObject.put("msg", "success");
				} else {
					// 验证码失效
					jsonObject.put("msg", "invalid");
				}
			}
		} catch (AuthenticationException e) {
			jsonObject.put("msg", "userDelete");
			return jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/**
	 * 微信自动登录
	 * 
	 * @param code
	 *            微信提供的code
	 * @return
	 */
	public String getTokenByCode(String code) {
		JSONObject jsonObject = new JSONObject();

		// 取得openid
		JSONObject rsp = null;
		try {
			// 获取微信信息
			rsp = getWechatUserInfo(code);
		} catch (Exception e) {
			jsonObject.put("msg", "timeout");
			return jsonObject.toString();
		}
		// 查出user，返回token和user
		Subject subject = SecurityUtils.getSubject();
		// 获取token
		UserToken token = new UserToken(rsp.getString("openid"), Const.NOPASSWORD);
		subject.login(token);

		Criteria<Users> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("wechatOpenId", rsp.getString("openid"), true));
		List<Users> users = userRepository.findAll(criteria);

		if (null != users && users.size() > 0) {
			Users user = users.get(0);
			// 保存WechatSessionId
			user.setWechatSessionId(subject.getSession().getId().toString());
			user.setUpdateDate(DateUtil.format(new Date()));
			userRepository.save(user);

			jsonObject.put("token", subject.getSession().getId());
			jsonObject.put("user", user);
		}
		return jsonObject.toString();
	}

	/**
	 * 课程预约数量检索
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public Object searchCourseReserveCount(Long courseId) {
		CourseReserveDto courseReserve = new CourseReserveDto();
		courseReserve.setCourseId(courseId);
		Map<String, Object> result = courseReserveService.search(courseReserve);
		return result.get("count");
		// return 10;
	}

	/**
	 * 课程预约
	 * 
	 * @param id
	 * @throws UnsupportedEncodingException
	 */
	public String courseReserve(String formId, String phoneNumber, Long courseId, String code) {

		// 返回信息
		JSONObject jsonObject = new JSONObject();
		try {
			// 取得微信token
			getAccess_token();
			// 发送信息
			jsonObject = sendAppMessage(formId, phoneNumber, courseId, code);
		} catch (Exception e) {
			jsonObject.put("msg", "timeout");
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	/**
	 * 发送小程序信息
	 * 
	 * @param formId
	 * @param phoneNumber
	 * @param courseId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public JSONObject sendAppMessage(String formId, String phoneNumber, Long courseId, String code) throws Exception {

		JSONObject rsp = null;
		// 获取微信信息
		try {
			rsp = getWechatUserInfo(code);
		} catch (Exception e) {
			throw e;
		}

		// 取得课程
		Course course = courseService.searchSingle(courseId);

		JSONObject jsonObject = new JSONObject();
		if (course != null) {
			// 取得预约码
			String reserveCode = getCode("yy");
			// 发送消息
			String sendMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="
					+ WechatConstData.getAccess_token();
			CloseableHttpClient httpClient = HttpClients.createDefault();
			JSONObject mRsp = null;
			HttpPost post = new HttpPost(sendMessageUrl);

			// 设置post的数据
			JSONObject postData = new JSONObject();
			postData.put("touser", rsp.getString("openid"));
			postData.put("template_id", "GILld8uMTyyqIYSk5cL5-w6EfCgd7gpqIN9gPhNumVE");
			postData.put("form_id", formId);
			Map<String, DataValue> data = new HashMap<String, DataValue>();

			DataValue dataValue1 = new DataValue();
			dataValue1.setValue(course.name);
			data.put("keyword1", dataValue1);

			DataValue dataValue2 = new DataValue();

			dataValue2.setValue(reserveCode);
			data.put("keyword2", dataValue2);

			DataValue dataValue3 = new DataValue();
			dataValue3.setValue(phoneNumber);
			data.put("keyword3", dataValue3);

			DataValue dataValue4 = new DataValue();
			dataValue4.setValue("请到学校缴费报名");
			data.put("keyword4", dataValue4);

			postData.put("data", data);
			post.setEntity(new StringEntity(postData.toString(), Charset.forName("UTF-8")));
			CloseableHttpResponse messageRsp = null;
			try {
				messageRsp = httpClient.execute(post);
				if (messageRsp != null && messageRsp.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = messageRsp.getEntity();
					mRsp = JSONObject.fromObject(EntityUtils.toString(entity, "UTF-8"));
				}
			} catch (IOException e) {
				jsonObject.put("msg", "timeout");
				return jsonObject;
			} finally {
				try {
					httpClient.close();
					if (messageRsp != null) {
						messageRsp.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (mRsp != null) {
				if (mRsp.getString("errmsg").equals("ok")) {
					// 消息发送成功，将数据存入db
					CourseReserve courseReserve = new CourseReserve();
					courseReserve.setCourseId(courseId);
					courseReserve.setReserveCode(reserveCode);
					courseReserve.setPhoneNumber(phoneNumber);
					courseReserveService.creat(courseReserve);
					jsonObject.put("msg", "success");
				}
				;
			}
		} else {
			jsonObject.put("msg", "timeout");
		}

		return jsonObject;
	}

	/**
	 * 获取微信用户信息：openid sessionid等
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public JSONObject getWechatUserInfo(String code) throws IOException {

		// 发送请求到微信取得信息
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx57402a8082a9ca71"
				+ "&secret=489740b67838ee9638a69b732b8a2f83" + "&js_code=" + code + "&grant_type=authorization_code";

		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return JSONObject.fromObject(result);
	}

	/**
	 * 获取Access_token
	 * 
	 * @return
	 * @throws IOException
	 */
	public void getAccess_token() throws IOException {
		// 当前时间取得
		Long now = new Date().getTime();
		// 是否有token或时间是否过期
		if (WechatConstData.getAccess_token() == null
				|| !(WechatConstData.getAccess_token() != null && WechatConstData.check_Access_token_extdate(now))) {

			// 获取access_token
			// 发送请求到微信取得信息
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
					+ "&appid=wx57402a8082a9ca71" + "&secret=489740b67838ee9638a69b732b8a2f83";

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(get);
				if (response != null && response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					JSONObject rsp = JSONObject.fromObject(EntityUtils.toString(entity));
					// 将token和时间存到单例对象中
					WechatConstData.setAccess_token(rsp.getString("access_token"));
					WechatConstData.setAccess_token_extdate(now + Long.valueOf(rsp.getString("expires_in")));
				}
			} catch (IOException e) {
				throw e;
			} finally {
				try {
					httpClient.close();
					if (response != null) {
						response.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 生成随机code
	 * 
	 * @param title
	 *            code标题
	 * @return
	 */
	public String getCode(String title) {
		// 时间戳后6位
		Long nowCode = new Date().getTime() % 1000000;
		// 随机6位数字
		int code = (int) ((Math.random() * 9 + 1) * 100000);
		return title + nowCode.toString() + code;
	}

	/**
	 * 设置json数据时使用
	 * 
	 * @author Administrator
	 *
	 */
	public class DataValue {

		public String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}

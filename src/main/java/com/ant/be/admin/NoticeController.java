package com.ant.be.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.dto.NoticeDto;
import com.ant.be.entity.Notice;
import com.ant.be.service.NoticeService;

/**
 * 通知
 * @author xujianxia
 *
 */

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/**
	 * 单例查询
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getNotice(@PathVariable("id") Long id) {
		return noticeService.searchSingle(id);
	}

	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object query(@RequestBody NoticeDto noticeDto) throws ClassNotFoundException {
		return noticeService.search(noticeDto);
	}
	
	/**
	 * 分页查询返回数据
	 * 
	 * @param pg
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Object wechatQuery(@ModelAttribute NoticeDto noticeDto) throws ClassNotFoundException {
		Map<String, Object> map = noticeService.search(noticeDto);
		return map;
	}


	/**
	 * 编辑
	 * 
	 * @param role
	 */
	@Transactional
	@Autowired(required = false)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody NoticeDto noticeDto) {
		return noticeService.update(id, noticeDto, "update");
	}


	/**
	 * 新建
	 * 
	 * @param role
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Notice notice) throws Exception{
		return noticeService.creat(notice);
	}


	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return noticeService.update(id, null, "delete");
	}

	
}

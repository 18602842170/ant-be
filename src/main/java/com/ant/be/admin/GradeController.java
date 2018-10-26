package com.ant.be.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.dto.GradeDto;
import com.ant.be.entity.Grade;
import com.ant.be.service.GradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 班级
 * @author xujianxia
 *
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	/**
	 * 班级信息页面初期查询
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PATCH)
	public Object searchGradeList(@RequestBody GradeDto dto) throws Exception {

		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		
		// 调用service里的方法检索数据
		List<GradeDto> gradeList = gradeService.search(dto);
		
		PageInfo<GradeDto> p = new PageInfo<GradeDto>(gradeList);
		
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());	// 总数
		map.put("results", p.getList()); // 结果集	
		map.put("next", null); 
		map.put("previous", null);
		return map;
	}
	
	/**
	 * 新建学员
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object creat(@RequestBody Grade grade) throws Exception {
		return gradeService.creat(grade);
	}
	
	/**
	 * 更新学员
	 * 
	 * @param student
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @RequestBody Grade grade){
		return gradeService.update(id, grade, Const.update);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		return gradeService.update(id, null, Const.delete);
	}
	
	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable("id") Long id) throws ParseException {
		return gradeService.findGradeById(id);
	}
	
}

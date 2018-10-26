package com.ant.be.service;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.CourseAllotDto;
import com.ant.be.entity.CourseAllot;
import com.ant.be.mapper.CourseAllotMapper;
import com.ant.be.repository.CourseAllotRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class CourseAllotService {
	
	@Autowired
	private CourseAllotRepostory courseAllotRepostory;

	@Autowired
	private CourseAllotMapper courseAllotMapper;
	

	/**
	 * 检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(CourseAllotDto courseAllotDto) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(courseAllotDto.getPageNum(), courseAllotDto.getPageSize());
		
		List<CourseAllotDto> courses = courseAllotMapper.searchCourseAllot(courseAllotDto.getUserId(),courseAllotDto.getCourseId(),courseAllotDto.getUserType());
		PageInfo<CourseAllotDto> p = new PageInfo<CourseAllotDto>(courses);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());
		map.put("results", p.getList());
		map.put("next", null);
		map.put("previous", null);

		return map;
	}
	
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	public CourseAllot searchSingle(Long id){
		Optional<CourseAllot> courseDetail = courseAllotRepostory.findById(id);
		return courseDetail.get();
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public CourseAllot creat(CourseAllot courseAllot) {
		courseAllot.setIsCharge(courseAllot.getIsCharge());
		courseAllot.setCourseId(courseAllot.getCourseId());
		courseAllot.setUserId(courseAllot.getUserId());
		courseAllot.setUserType(courseAllot.getUserType());
		courseAllot.setDeleteFlg(false);
		courseAllot.setCreatDate(DateUtil.format(new Date()));
		courseAllot.setUpdateDate(DateUtil.format(new Date()));
		return courseAllotRepostory.save(courseAllot);
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public CourseAllot update(Long id, CourseAllot courseAllot, String flg) {
		Optional<CourseAllot> findCourseAllot = courseAllotRepostory.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(courseAllot, findCourseAllot.get());
			findCourseAllot.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			findCourseAllot.get().setDeleteFlg(true);
		}
		findCourseAllot.get().setUpdateDate(DateUtil.format(new Date()));
		return courseAllotRepostory.save(findCourseAllot.get());
	}
	/**
	 * 更新教师报名信息
	 * 
	 * @param id
	 * @return
	 */
	public Object updateByCourseId(CourseAllot courseAllot) {
		
		List<CourseAllotDto> courses = courseAllotMapper.searchCourseAllot(null,courseAllot.getCourseId(),courseAllot.getUserType());
		
		if(courses !=null && courses.size()>0) {
			
			CourseAllot courseAllots=courses.get(0);
			courseAllots.setUserId(courseAllot.getUserId());
			
			this.update(courseAllots.getId(), courseAllots, "update");
			
		}
	
		return null;
	}



}

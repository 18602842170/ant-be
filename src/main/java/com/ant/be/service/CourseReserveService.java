package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.CourseReserveDto;
import com.ant.be.entity.CourseReserve;
import com.ant.be.repository.CourseReserveRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ant.be.mapper.CourseReserveMapper;

@Component
public class CourseReserveService {
	@Autowired
	private CourseReserveRepostory courseReserveRepostory;

	@Autowired
	private CourseReserveMapper courseReserveMapper;
	
	/**
	 * 检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(CourseReserveDto courseReserve) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(courseReserve.getPageNum(), courseReserve.getPageSize());

		if(!("").equals(courseReserve.getReserveCode()) && null != courseReserve.getReserveCode()) {
			courseReserve.setReserveCode("%"+courseReserve.getReserveCode()+"%");
		}

		if(!("").equals(courseReserve.getPhoneNumber()) && null != courseReserve.getPhoneNumber()) {
			courseReserve.setPhoneNumber("%"+courseReserve.getPhoneNumber()+"%");
		}
		
		if(courseReserve.getCourseId() == 0) {
			courseReserve.setCourseId(null);
		}
		List<CourseReserveDto> courses = courseReserveMapper.searchCourseReserve(courseReserve.getId(),courseReserve.getCourseId(),courseReserve.getReserveCode(),courseReserve.getPhoneNumber());
		PageInfo<CourseReserveDto> p = new PageInfo<CourseReserveDto>(courses);
		
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
	public CourseReserve searchSingle(Long id){
		Optional<CourseReserve> course = courseReserveRepostory.findById(id);
		return course.get();
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public CourseReserve creat(CourseReserve courseReserve) {
		courseReserve.setDeleteFlg(false);
		courseReserve.setCreatDate(DateUtil.format(new Date()));
		courseReserve.setUpdateDate(DateUtil.format(new Date()));
		return courseReserveRepostory.save(courseReserve);
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public CourseReserve update(Long id, CourseReserve courseReserve, String flg) {
		Optional<CourseReserve> findCourse = courseReserveRepostory.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(courseReserve, findCourse.get());
			findCourse.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			findCourse.get().setDeleteFlg(true);
		}
		findCourse.get().setUpdateDate(DateUtil.format(new Date()));
		return courseReserveRepostory.save(findCourse.get());
	}

}

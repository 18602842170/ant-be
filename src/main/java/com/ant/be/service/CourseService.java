package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.CourseDto;
import com.ant.be.entity.Course;
import com.ant.be.mapper.CourseMapper;
import com.ant.be.repository.CourseAllotRepository;
import com.ant.be.repository.CourseRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class CourseService {
	@Autowired
	private CourseAllotRepository courseAllotRepository;
	
	@Autowired
	private CourseRepostory courseRepostory;
	@Autowired
	private CourseMapper courseMapper;

	/**
	 * 检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(CourseDto course) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(course.getPageNum(), course.getPageSize());

		if(!("").equals(course.getName()) && null != course.getName()) {
			course.setName("%"+course.getName()+"%");
		}
		List<CourseDto> courses = null;
		if(null != course.searchPersonalCourseByUserId) {
			courses = courseMapper.searchPersonalCourse(course.searchPersonalCourseByUserId);	
		}else {
			courses = courseMapper.searchCourse(course.getId(),course.getName(),course.getFine()
					,course.getGradeId(),course.getSubjectId(),course.getSearchCourseDetailCount(),course.getSearchCourseReserveCount());
		}
		PageInfo<CourseDto> p = new PageInfo<CourseDto>(courses);
		
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
	public Course searchSingle(Long id){
		Optional<Course> course = courseRepostory.findById(id);
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
	public Course creat(Course course) {
		
		course.setName(course.getName());
		course.setSubjectId(course.getSubjectId());
		course.setGradeId(course.getGradeId());
		course.setClassesNumber(course.getClassesNumber());
		course.setPayNumber(course.getPayNumber());
		course.setAppointmentNumber(course.getAppointmentNumber());
		course.setDeleteFlg(false);
		course.setTeachers(course.getTeachers());
		course.setUserId(course.getUserId());
		course.setClassroomId(course.getClassroomId());
		course.setCreatDate(DateUtil.format(new Date()));
		course.setUpdateDate(DateUtil.format(new Date()));
		if(""!=course.getYear()&& course.getYear().length()>0) {
			
			course.setYear(course.getYear().split("-")[0]);
			
		}
		if("".equals(course.getBackImageUrl())||course.getBackImageUrl()==null || course.getBackImageUrl().length()==0) {
			course.setBackImageUrl("../../share/image/1.jpg");
			
		}
		return 	courseRepostory.save(course);
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public Course update(Long id, Course course, String flg) {
		Optional<Course> findCourse = courseRepostory.findById(id);
		if (flg.equals("update")) {
			if(""!=course.getYear()&& course.getYear().length()>0) {
				
				course.setYear(course.getYear().split("-")[0]);;
			}
			
			UpdateUtil.copyNonNullProperties(course, findCourse.get());
			findCourse.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			findCourse.get().setDeleteFlg(true);
		}
		findCourse.get().setUpdateDate(DateUtil.format(new Date()));
		return courseRepostory.save(findCourse.get());
	}


}

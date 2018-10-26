package com.ant.be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.admin.utils.TSMSUtils;
import com.ant.be.dto.TeacherDto;
import com.ant.be.entity.Teacher;
import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.mapper.TeacherMapper;
import com.ant.be.repository.TeacherRepository;
import com.ant.be.repository.UserRepository;
import com.ant.be.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeacherMapper teacherMapper;
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(TeacherDto dto) throws Exception {
		
		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(dto != null) {
			// 装载查询条件
			// 查询
			Criteria<Teacher> criteria = new Criteria<>();
			criteria.add(Restrictions.eq("deleteFlg", false, true));
			// 这个查询等效于社内系统中通过BeanMap condition来进行的查询，teacherRepository中的findAll是基础方法，criteria即类似BeanMap的condition
//			List<Teacher> teacherList  = teacherRepository.findAll(criteria);
			
//			注释掉的下面这行代码，用于mapper的自定义查询（即自己写sql查询）
			// 教师CD
			List<TeacherDto> teacherList = teacherMapper.searchTeachers(
					TSMSUtils.creaetLikeParama(dto.getTeacherName()), 
					dto.getTeacherCd(),
					dto.getTeacherLevel(),
					dto.getUserId()
					);
			
			if(teacherList != null && teacherList.size()>0) {
				
				for(TeacherDto t : teacherList) {
					if("0".equals(t.getTeacherSex())) {
						t.setTeacherSexStr("女");
					}else if("1".equals(t.getTeacherSex())) {
						t.setTeacherSexStr("男");
					}
					
					if("1".equals(t.getTeacherLevel())) {
						t.setTeacherLevelStr("实习教师");
					}else if("2".equals(t.getTeacherLevel())) {
						t.setTeacherLevelStr("一级教师");
					}else if("3".equals(t.getTeacherLevel())) {
						t.setTeacherLevelStr("二级教师");
					}else if("4".equals(t.getTeacherLevel())) {
						t.setTeacherLevelStr("特级教师");
					}
					
					if(!TSMSUtils.isEmpty(t.getTeacherNameInUser())) {
						t.setTeacherName(t.getTeacherNameInUser());
					}
				}
				
				// 查询到有结果
				PageInfo<TeacherDto> p = new PageInfo<TeacherDto>(teacherList);
				map.put("count", p.getTotal());	// 总数
				map.put("results", p.getList()); // 结果集
				map.put("next", null); 
				map.put("previous", null);
			}
		}
		
		return map;
	}
	
	/**
	 * 根据ID查找指定数据
	 * @param id
	 * @return
	 */
	public Teacher findTeacherById(Long id) throws Exception{
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if(teacher != null) {
			Teacher t = teacher.get();
			
			Optional<Users> user  = userRepository.findById(t.getUserId());
			if(user != null) {
				t.setTeacherName(user.get().getName());
			}
			
			return t;
		}
		return null;
	}

	/**
	 * 获取教师级别下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> searchTeacherLevel() throws Exception{
		return null;
	}

	/**
	 * 编辑教师
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Teacher updateTeacher(Long id,TeacherDto dto) throws Exception{
		
		if(dto != null) {
			
			Optional<Teacher> teacherOpt = teacherRepository.findById(id);
			if(teacherOpt != null) {
				Teacher t = teacherOpt.get();
				t.setTeacherCd(dto.getTeacherCd());
//				t.setTeacherName(dto.getTeacherName());
				t.setTeacherSex(dto.getTeacherSex());
				t.setTeacherLevel(dto.getTeacherLevel());
				t.setTeacherProfile(dto.getTeacherProfile());
				t.setUpdateDate(DateUtil.format(new Date()));
				
				return teacherRepository.save(t);
			}
		}
		
		return null;
	}

	/**
	 * 通过id查找教师
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Teacher findTeacherById(TeacherDto dto)throws Exception {
		if(dto != null && !TSMSUtils.isEmpty(dto.getTeacherId())) {
			Optional<Teacher> teacher = teacherRepository.findById((long)Integer.parseInt(dto.getTeacherId()));
			if(teacher != null) {
				
				Teacher t = teacher.get();
				
				Optional<Users> user  = userRepository.findById(t.getUserId());
				if(user != null) {
					t.setTeacherName(user.get().getName());
				}
				
				return t;
			}
		}
		return null;
	}
	
	
	/**
	 * 新建教师
	 * 
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public Teacher creat(Teacher teacher) throws Exception{
		teacher.setUserId(teacher.getUserId());
		teacher.setDeleteFlg(false);
		teacher.setCreatDate(DateUtil.format(new Date()));
		teacher.setUpdateDate(DateUtil.format(new Date()));
		return teacherRepository.save(teacher);
	}
}

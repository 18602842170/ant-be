package com.ant.be.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.ClassroomDto;
import com.ant.be.entity.Classroom;
import com.ant.be.mapper.ClassroomMapper;
import com.ant.be.repository.ClassroomRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;


/**
 * 教室服务
 * @author xujianxia
 *
 */
@Component
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private ClassroomMapper classroomMapper;
		
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public List<ClassroomDto> search(ClassroomDto dto) throws Exception {
		
		if(dto != null) {
			// 装载查询条件
//			Criteria<Student> criteria = new Criteria<>();
//			// 学员账号
//			if(!CheckUtils.isEmpty(dto.getStudentNumber())) {
//				criteria.add(Restrictions.eq("studentNumber", dto.getStudentNumber(), true));
//			}
//			// 学员姓名
//			if(!CheckUtils.isEmpty(dto.getStudentName())) {
//				criteria.add(Restrictions.eq("studentName", dto.getStudentName(), true));
//			}
//			if(!CheckUtils.isEmpty(dto.getStudentSchool())) {
//				criteria.add(Restrictions.eq("studentShool", dto.getStudentSchool(), true));
//			}
//			criteria.add(Restrictions.eq("deleteFlg", false, true));
			
			if(!("").equals(dto.getClassroomName()) && null != dto.getClassroomName()) {
				dto.setClassroomName("%"+dto.getClassroomName()+"%");
			}
			if(!("").equals(dto.getClassroomAddress()) && null != dto.getClassroomAddress()) {
				dto.setClassroomAddress("%"+dto.getClassroomAddress()+"%");
			}
			
//			注释掉的下面这行代码，用于mapper的自定义查询（即自己写sql查询）			
			List<ClassroomDto> classroomList = classroomMapper.searchClassrooms(dto.getClassroomName(),dto.getClassroomAddress(),dto.getClassroomPeople());
			
			if(classroomList != null && classroomList.size()>0) {
				// 查询到有结果
				return classroomList;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * 根据ID查找指定数据
	 * @param id
	 * @return
	 */
	public Optional<Classroom> findClassroomById(Long id){
		Optional<Classroom> classroom = classroomRepository.findById(id);
		return classroom;
	}
	
	/**
	 * 新建教室
	 * 
	 * @param Classroom
	 * @return
	 * @throws Exception
	 */
	public Classroom creat(Classroom classroom) throws Exception{
		classroom.setClassroomName(classroom.getClassroomName());
		classroom.setClassroomPeople(classroom.getClassroomPeople());
		classroom.setClassroomAddress(classroom.getClassroomAddress());
		classroom.setDeleteFlg(false);
		classroom.setCreatDate(DateUtil.format(new Date()));
		classroom.setUpdateDate(DateUtil.format(new Date()));
		return classroomRepository.save(classroom);
	}
	
	/**
	 * 更新教室
	 * 
	 * @param id
	 * @return
	 */
	public Classroom update(Long id, Classroom classroom, String flg) {
		Optional<Classroom> classroomDec = classroomRepository.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(classroom, classroomDec.get());
			classroomDec.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			classroomDec.get().setDeleteFlg(true);
		}
		classroomDec.get().setUpdateDate(DateUtil.format(new Date()));
		return classroomRepository.save(classroomDec.get());
	}
	
}

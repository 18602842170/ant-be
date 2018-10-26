package com.ant.be.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.StudentDto;
import com.ant.be.entity.Student;
import com.ant.be.mapper.StudentMapper;
import com.ant.be.repository.CourseAllotRepostory;
import com.ant.be.repository.CourseDetailRepostory;
import com.ant.be.repository.CourseRepostory;
import com.ant.be.repository.StudentRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;


/**
 * 学员服务
 * @author xujianxia
 *
 */
@Component
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CourseRepostory courseRepostory;
	@Autowired
	private CourseDetailRepostory courseDetailRepostory;
	@Autowired
	private CourseAllotRepostory courseAllotRepostory;
	
	
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public List<StudentDto> search(StudentDto dto) throws Exception {
		
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
			
			if(!("").equals(dto.getStudentName()) && null != dto.getStudentName()) {
				dto.setStudentName("%"+dto.getStudentName()+"%");
			}
			if(!("").equals(dto.getStudentSchool()) && null != dto.getStudentSchool()) {
				dto.setStudentSchool("%"+dto.getStudentSchool()+"%");
			}
			// 这个查询等效于社内系统中通过BeanMap condition来进行的查询，teacherRepository中的findAll是基础方法，criteria即类似BeanMap的condition
//			List<Teacher> teacherList  = teacherRepository.findAll(criteria);
			
//			注释掉的下面这行代码，用于mapper的自定义查询（即自己写sql查询）			
			List<StudentDto> studentList = studentMapper.searchStudents(dto.getStudentName(),dto.getStudentSchool(),dto.getUserId());
			
			if(studentList != null && studentList.size()>0) {
				// 查询到有结果
				return studentList;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * 根据ID查找指定数据
	 * @param id
	 * @return
	 */
	public StudentDto findStudentById(Long id){
		List<StudentDto> studentList = studentMapper.searchStudentById(id);
		return studentList.get(0);
	}
	
	/**
	 * 新建学员
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public Student creat(Student student) throws Exception{
		student.setUserId(student.getUserId());
		student.setDeleteFlg(false);
		student.setCreatDate(DateUtil.format(new Date()));
		student.setUpdateDate(DateUtil.format(new Date()));
		return studentRepository.save(student);
	}
	
	/**
	 * 更新学员
	 * 
	 * @param id
	 * @return
	 */
	public Student update(Long id, Student student, String flg) {
		Optional<Student> studentDec = studentRepository.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(student, studentDec.get());
			studentDec.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			studentDec.get().setDeleteFlg(true);
		}
		studentDec.get().setUpdateDate(DateUtil.format(new Date()));
		return studentRepository.save(studentDec.get());
	}
	
}

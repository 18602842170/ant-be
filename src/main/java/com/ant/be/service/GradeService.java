package com.ant.be.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.GradeDto;
import com.ant.be.dto.StudentDto;
import com.ant.be.entity.Grade;
import com.ant.be.entity.Student;
import com.ant.be.mapper.GradeMapper;
import com.ant.be.mapper.StudentMapper;
import com.ant.be.repository.CourseAllotRepostory;
import com.ant.be.repository.CourseDetailRepostory;
import com.ant.be.repository.CourseRepostory;
import com.ant.be.repository.GradeRepository;
import com.ant.be.repository.StudentRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;


/**
 * 班级服务
 * @author xujianxia
 *
 */
@Component
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private GradeMapper gradeMapper;
	
	
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public List<GradeDto> search(GradeDto dto) throws Exception {
		
		if(dto != null) {				
//			注释掉的下面这行代码，用于mapper的自定义查询（即自己写sql查询）			
			List<GradeDto> gradeList = gradeMapper.searchGrade(dto.getSearchYear(),
					dto.getSearchMonth(),
					dto.getSearchDay(),dto.getDistiguishId());
			
			if(gradeList != null && gradeList.size()>0) {
				// 查询到有结果
				for(int i=0;i<gradeList.size();i++) {
					if(gradeList.get(i).getEndDate()!=null) {
						gradeList.get(i).setEndTime(getStringTime(gradeList.get(i).getEndDate()));
					}else {
						gradeList.get(i).setEndTime("");
					}
					
					if(gradeList.get(i).getStartDate()!=null) {
						gradeList.get(i).setStartTime(getStringTime(gradeList.get(i).getStartDate()));
					}else {
						gradeList.get(i).setStartTime("");
					}
					
					if(gradeList.get(i).getGradeYear()!=null) {
						gradeList.get(i).setYearTime(getStringYear(gradeList.get(i).getGradeYear()));
					}else {
						gradeList.get(i).setYearTime("");
					}
					
					
					
				}
				return gradeList;
			}	
		}
		
		return null;
		
	}
	
	/**
	 * 根据ID查找指定数据
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public GradeDto findGradeById(Long id){
		List<GradeDto> gradeList = gradeMapper.searchGradeById(id);
		return gradeList.get(0);
	}
	
	/**
	 * 新建班级
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public Grade creat(Grade grade) throws Exception{
		grade.setDeleteFlg(false);
		grade.setStartDate(grade.getStartDate());
		grade.setEndDate(grade.getEndDate());
		grade.setCreatDate(DateUtil.format(new Date()));
		grade.setUpdateDate(DateUtil.format(new Date()));
		return gradeRepository.save(grade);
	}
	
	/**
	 * 更新学员
	 * 
	 * @param id
	 * @return
	 */
	public Grade update(Long id, Grade grade, String flg) {
		Optional<Grade> gradeDesc = gradeRepository.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(grade, gradeDesc.get());
			gradeDesc.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			gradeDesc.get().setDeleteFlg(true);
		}
		gradeDesc.get().setUpdateDate(DateUtil.format(new Date()));
		return gradeRepository.save(gradeDesc.get());
	}
	
	public static String getStringYear(Date _date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(_date);
		return dateString;
	}
	
	public static String getStringTime(Date _date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(_date);
		return dateString;
	}
	
}

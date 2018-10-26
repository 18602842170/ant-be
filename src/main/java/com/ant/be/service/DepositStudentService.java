package com.ant.be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.admin.utils.TSMSUtils;
import com.ant.be.dto.DepositStudentDto;
import com.ant.be.entity.DepositStudent;
import com.ant.be.mapper.DepositStudentMapper;
import com.ant.be.repository.DepositStudentRepository;
import com.ant.be.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class DepositStudentService {

	@Autowired
	private DepositStudentRepository depositStudentRepository;
	@Autowired
	private DepositStudentMapper depositStudentMapper;
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(DepositStudentDto dto) throws Exception {
		
		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(dto != null) {
			List<DepositStudentDto> depositList;
			// 查询
			if(dto.getTeacherUserId() == null) {
				depositList = depositStudentMapper.searchDepositStudentList(
						dto.studentUserId,
						TSMSUtils.creaetLikeParama(dto.getStudentNameInUser()), 
						TSMSUtils.creaetLikeParama(dto.getClassRoomName()),
						TSMSUtils.creaetLikeParama(dto.getFoodFlag()),
						null
						);
			}else {
				depositList = depositStudentMapper.searchStudentByTecherId(dto.getTeacherUserId());
			}
			// 查询到有结果
			PageInfo<DepositStudentDto> p = new PageInfo<DepositStudentDto>(depositList);
			map.put("count", p.getTotal());	// 总数
			map.put("results", p.getList()); // 结果集
			map.put("next", null); 
			map.put("previous", null);
		}
		
		return map;
	}
	
	/**
	 * 根据ID查找指定数据
	 * @param id
	 * @return
	 */
	public DepositStudentDto findDepositById(Long id) throws Exception{
		// 查询
		List<DepositStudentDto> depositList = depositStudentMapper.searchDepositStudentList(
				null,
				null,
				null, 
				null,
				id
				);
		if(depositList != null && depositList.size()>0) {
			// 查询到有结果
			return depositList.get(0);
		}
		
		return null;
		
	}

	/**
	 * 编辑
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public DepositStudent updateDeposit(Long id,DepositStudentDto dto) throws Exception{
		
		if(dto != null) {
			
			Optional<DepositStudent> d = depositStudentRepository.findById(id);
			if(d != null) {
				DepositStudent t = d.get();
				t.setStudentUserId(dto.getStudentUserId());
				t.setClassRoomId(dto.getClassRoomId());
				t.setFoodFlag(dto.getFoodFlag());
				t.setDepositStartDate(dto.getDepositStartDate());
				t.setDepositEndDate(dto.getDepositEndDate());
				t.setUpdateDate(DateUtil.format(new Date()));
				
				return depositStudentRepository.save(t);
			}
		}
		
		return null;
	}

	
	/**
	 * 新建
	 * 
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public DepositStudent creat(DepositStudentDto dto) throws Exception{
		DepositStudent d = new DepositStudent();
		d.setStudentUserId(dto.getStudentUserId());
		d.setClassRoomId(dto.getClassRoomId());
		d.setDepositStartDate(dto.getDepositStartDate());
		d.setDepositEndDate(dto.getDepositEndDate());
		d.setFoodFlag(dto.getFoodFlag());
		d.setDeleteFlg(false);
		d.setCreatDate(DateUtil.format(new Date()));
		d.setUpdateDate(DateUtil.format(new Date()));
		return depositStudentRepository.save(d);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public DepositStudent delete(Long id) {
		Optional<DepositStudent> d = depositStudentRepository.findById(id);
		if(d != null) {
			DepositStudent t = d.get();
			t.setDeleteFlg(true);
			t.setUpdateDate(DateUtil.format(new Date()));
			
			return depositStudentRepository.save(t);
		}
		return null;
	}
}

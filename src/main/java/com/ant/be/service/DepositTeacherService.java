package com.ant.be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.admin.utils.TSMSUtils;
import com.ant.be.dto.DepositTeacherDto;
import com.ant.be.entity.DepositTeacher;
import com.ant.be.mapper.DepositTeacherMapper;
import com.ant.be.repository.DepositTeacherRepository;
import com.ant.be.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class DepositTeacherService {
	@Autowired
	private DepositTeacherRepository depositTeacherRepository;
	@Autowired
	private DepositTeacherMapper depositTeacherMapper;
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(DepositTeacherDto dto) throws Exception {
		
		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(dto != null) {
			// 查询
			List<DepositTeacherDto> depositList = depositTeacherMapper.searchDepositTeacherList(
					TSMSUtils.creaetLikeParama(dto.getTeacherNameInUser()), 
					TSMSUtils.creaetLikeParama(dto.getClassRoomName()),
					null);
			
			if(depositList != null && depositList.size()>0) {
				// 查询到有结果
				PageInfo<DepositTeacherDto> p = new PageInfo<DepositTeacherDto>(depositList);
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
	public DepositTeacherDto findDepositById(Long id) throws Exception{
		
		// 查询
		List<DepositTeacherDto> depositList = depositTeacherMapper.searchDepositTeacherList(
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
	public DepositTeacher updateDeposit(Long id,DepositTeacherDto dto) throws Exception{
		
		if(dto != null) {
			
			Optional<DepositTeacher> d = depositTeacherRepository.findById(id);
			if(d != null) {
				DepositTeacher t = d.get();
				t.setTeacherUserId(dto.getTeacherUserId());
				t.setClassRoomId(dto.getClassRoomId());
				t.setDepositStartDate(dto.getDepositStartDate());
				t.setDepositEndDate(dto.getDepositEndDate());
				t.setUpdateDate(DateUtil.format(new Date()));
				
				return depositTeacherRepository.save(t);
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
	public DepositTeacher creat(DepositTeacherDto dto) throws Exception{
		DepositTeacher d = new DepositTeacher();
		d.setTeacherUserId(dto.getTeacherUserId());
		d.setClassRoomId(dto.getClassRoomId());
		d.setDepositStartDate(dto.getDepositStartDate());
		d.setDepositEndDate(dto.getDepositEndDate());
		d.setDeleteFlg(false);
		d.setCreatDate(DateUtil.format(new Date()));
		d.setUpdateDate(DateUtil.format(new Date()));
		return depositTeacherRepository.save(d);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public DepositTeacher delete(Long id) {
		Optional<DepositTeacher> d = depositTeacherRepository.findById(id);
		if(d != null) {
			DepositTeacher t = d.get();
			t.setDeleteFlg(true);
			t.setUpdateDate(DateUtil.format(new Date()));
			
			return depositTeacherRepository.save(t);
		}
		return null;
	}
}

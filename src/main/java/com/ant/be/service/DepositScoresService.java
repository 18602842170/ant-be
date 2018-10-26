package com.ant.be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.DepositScoresDto;
import com.ant.be.entity.DepositScores;
import com.ant.be.mapper.DepositScoresMapper;
import com.ant.be.repository.DepositScoresRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class DepositScoresService {

	@Autowired
	private DepositScoresRepository depositScoresRepository;
	@Autowired
	private DepositScoresMapper depositScoresMapper;
	
	/**
	 * 根据条件全检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(DepositScoresDto dto) {
		
		// 行页设置,dto中取到前台传回的分页设置
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(dto != null) {
			List<DepositScoresDto> depositList;
			// 查询
			depositList = depositScoresMapper.searchDepositScoresList(dto.depositStudentId, dto.scoresDateString);
			// 查询到有结果
			PageInfo<DepositScoresDto> p = new PageInfo<DepositScoresDto>(depositList);
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
	public DepositScores searchSingle(Long id){
		Optional<DepositScores> depositScores = depositScoresRepository.findById(id);
		return depositScores.get();
		
	}
	
	/**
	 * 新建学员
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public DepositScores creat(DepositScoresDto depositScores){
		DepositScores saveEntity = new DepositScores();
		UpdateUtil.copyNonNullProperties(depositScores, saveEntity);
		saveEntity.setDeleteFlg(false);
		saveEntity.setCreatDate(DateUtil.format(new Date()));
		saveEntity.setUpdateDate(DateUtil.format(new Date()));
		return depositScoresRepository.save(saveEntity);
	}
	
	/**
	 * 更新学员
	 * 
	 * @param id
	 * @return
	 */
	public DepositScores update(Long id, DepositScoresDto depositScores, String flg) {
		Optional<DepositScores> depositS = depositScoresRepository.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(depositScores, depositS.get());
			depositS.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			depositS.get().setDeleteFlg(true);
		}
		depositS.get().setUpdateDate(DateUtil.format(new Date()));
		return depositScoresRepository.save(depositS.get());
	}

}

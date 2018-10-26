package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.RollCallDto;
import com.ant.be.entity.RollCall;
import com.ant.be.mapper.RollCallMapper;
import com.ant.be.repository.RollCallRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class RollCallService {
	

	
	@Autowired
	private RollCallRepostory rollCallRepostory;

	@Autowired
	private RollCallMapper rollCallMapper;
	

	/**
	 * 检索
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(RollCallDto rollCallDto) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(rollCallDto.getPageNum(), rollCallDto.getPageSize());
			
		List<RollCallDto> rollCalls = rollCallMapper.searchRollCall(
				rollCallDto.getUserId(),
				rollCallDto.getCourseId(),
				rollCallDto.getSearchYear(),
				rollCallDto.getSearchMonth(), 
				rollCallDto.getSearchDay(),
				rollCallDto.getCourseDetailId(),
				rollCallDto.getId()
				);
		if(rollCalls!=null &&rollCalls.size()>0) {
			for(int i=0;i<rollCalls.size();i++) {
				if(rollCalls.get(i).getCourseDay()!=null) {
					rollCalls.get(i).setCourseDayStr(getStringDate(rollCalls.get(i).getCourseDay()));
				}else {
					rollCalls.get(i).setCourseDayStr("");
				}
				
			}
		}
		PageInfo<RollCallDto> p = new PageInfo<RollCallDto>(rollCalls);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", p.getTotal());
		map.put("results", p.getList());
		map.put("next", null);
		map.put("previous", null);

		return map;
	}
	
	public static String getStringDate(Date _date) {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	     String dateString = formatter.format(_date);
	     return dateString;
	}
	
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	public RollCall searchSingle(Long id){
		Optional<RollCall> rollCall = rollCallRepostory.findById(id);
		return rollCall.get();
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public RollCall creat(RollCall rollCall) {
		rollCall.setDeleteFlg(false);
		rollCall.setCreatDate(DateUtil.format(new Date()));
		rollCall.setUpdateDate(DateUtil.format(new Date()));
		return rollCallRepostory.save(rollCall);
	}

	/**
	 * 批量新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void creatBySaveList(RollCallDto rollCallDto) {
		JSONArray jsonArray = JSONArray.fromObject(rollCallDto.getSaveList());
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jobj = jsonArray.getJSONObject(i); 
			RollCall rollCall;
			if(jobj.get("id") != null && !jobj.get("id").equals("null")) {
				Optional<RollCall> findRollCall = rollCallRepostory.findById( Long.parseLong(jobj.get("id").toString()));
				rollCall = findRollCall.get();
				rollCall.setIsToClass((Boolean)jobj.get("checked"));
				rollCall.setUpdateDate(DateUtil.format(new Date()));
				rollCallRepostory.save(rollCall);
			}else {
				rollCall = new RollCall();
				rollCall.setUserId(Long.parseLong(jobj.get("userId").toString()));
				rollCall.setCourseDetailId(Long.parseLong(jobj.get("courseDetailId").toString()));
				rollCall.setIsToClass((Boolean)jobj.get("checked"));
				rollCall.setDeleteFlg(false);
				rollCall.setCreatDate(DateUtil.format(new Date()));
				rollCall.setUpdateDate(DateUtil.format(new Date()));
				rollCallRepostory.save(rollCall);
			}
		}
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public RollCall update(Long id, RollCall rollCallDto, String flg) {
		Optional<RollCall> findRollCall = rollCallRepostory.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(rollCallDto, findRollCall.get());
			findRollCall.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			findRollCall.get().setDeleteFlg(true);
		}
		findRollCall.get().setUpdateDate(DateUtil.format(new Date()));
		return rollCallRepostory.save(findRollCall.get());
	}
}

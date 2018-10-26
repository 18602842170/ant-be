package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ant.be.admin.utils.TSMSUtils;
import com.ant.be.dto.DistinguishDto;
import com.ant.be.dto.StudentDto;
import com.ant.be.dto.TeacherDto;
import com.ant.be.entity.Distinguish;
import com.ant.be.entity.Student;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.mapper.DistinguishMapper;
import com.ant.be.mapper.StudentMapper;
import com.ant.be.repository.DistinguishRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.ToolUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class DistinguishService {
	@Autowired
	private DistinguishRepostory distinguishRepostory;
	
	@Autowired
	private DistinguishMapper distinguishMapper;

	/**
	 * 检索
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(DistinguishDto constDto) {
		PageHelper.startPage(constDto.getPageNum(), constDto.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		Criteria<Distinguish> criteria = new Criteria<>();
		if (!("").equals(constDto.getCd()) && null != constDto.getCd()) {
			criteria.add(Restrictions.eq("cd", constDto.getCd(), true));
			criteria.add(Restrictions.eq("deleteFlg", false, true));
			criteria.add(Restrictions.like("tpName",constDto.getTpName(), true));
		}
		if(constDto != null) {
			
			if(!("").equals(constDto.getCd()) && null != constDto.getCd()) {
				constDto.setCd(constDto.getCd());
			}
			if(!("").equals(constDto.getTpName()) && null != constDto.getTpName()) {
				constDto.setTpName("%"+constDto.getTpName()+"%");
			}
			
			if(!("").equals(constDto.getCreateName()) && null != constDto.getCreateName()) {
				constDto.setCreateName("%"+constDto.getCreateName()+"%");
			}
			
//			注释掉的下面这行代码，用于mapper的自定义查询（即自己写sql查询）			
			List<DistinguishDto> DistinguishList = distinguishMapper.searchDistinguishList(constDto.getCd(),
					constDto.getTpName(),
					constDto.getCreateName());
			// 查询到有结果
			if (DistinguishList != null && DistinguishList.size() > 0) {
				for (int i = 0; i < DistinguishList.size(); i++) {
					if (DistinguishList.get(i).getCreatDate() != null) {
						DistinguishList.get(i).setCreateDateStr(getStringDate(DistinguishList.get(i).getCreatDate()));
					} else {
						DistinguishList.get(i).setCreateDateStr("");
					}
					
					if (DistinguishList.get(i).getUpdateDate() != null) {
						DistinguishList.get(i).setUpdateDateStr(getStringDate(DistinguishList.get(i).getUpdateDate()));
					} else {
						DistinguishList.get(i).setUpdateDateStr("");
					}

				}
			}
			
			PageInfo<DistinguishDto> p = new PageInfo<DistinguishDto>(DistinguishList);
			map.put("count", p.getTotal());	// 总数
			map.put("results", p.getList()); // 结果集
			map.put("next", null); 
			map.put("previous", null);
		}
		
		return map;
	}
	
	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	public Distinguish searchSingle(Long id) {
		Optional<Distinguish> consts = distinguishRepostory.findById(id);
		return consts.get();
	}

	/**
	 * 新建
	 * @param constDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public Distinguish creat(DistinguishDto constDto) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Distinguish consts = new Distinguish();
		ToolUtil.copyProperties(constDto, consts);
		consts.setDeleteFlg(false);
		consts.setCreateUserId(constDto.getCreateUserId());
		consts.setUpdateUserId(constDto.getUpdateUserId());
		consts.setCreatDate(DateUtil.format(new Date()));
		consts.setUpdateDate(DateUtil.format(new Date()));
		return distinguishRepostory.save(consts);
		
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Distinguish update(Long id, DistinguishDto consts, String flg) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Optional<Distinguish> constDes = distinguishRepostory.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(consts, constDes.get());
			constDes.get().setUpdateUserId(consts.getUpdateUserId());
		} else if (flg.equals("delete")) {
			constDes.get().setDeleteFlg(true);
		}
		constDes.get().setUpdateDate(DateUtil.format(new Date()));
		return distinguishRepostory.save(constDes.get());
	}
	
	public static String getStringDate(Date _date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(_date);
		return dateString;
	}
}

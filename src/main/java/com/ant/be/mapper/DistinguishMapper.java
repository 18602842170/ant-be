package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.DistinguishDto;
import com.ant.be.dto.NoticeDto;

public interface DistinguishMapper {

	public List<DistinguishDto> searchDistinguishList(
			@Param("cd") String cd,
			@Param("tpName") String tpName,
			@Param("createName") String createName);

}

package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.NoticeDto;

public interface NoticeMapper {

	public List<NoticeDto> searchNoticeList(
			@Param("schoolNoticeTargets") String schoolNoticeTargets,
			@Param("noticeTitle") String noticeTitle,
			@Param("publishName") String publishName,
			@Param("searchYear") int searchYear,
			@Param("searchMonth") int searchMonth,
			@Param("searchDay") int searchDay);

}

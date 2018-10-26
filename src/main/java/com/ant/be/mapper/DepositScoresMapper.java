package com.ant.be.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ant.be.dto.DepositScoresDto;

public interface DepositScoresMapper {

	public List<DepositScoresDto> searchDepositScoresList(
			@Param("depositStudentId") Long depositStudentId,
			@Param("scoresDateString") String scoresDateString);

}

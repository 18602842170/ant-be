package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.DepositScores;

public interface DepositScoresRepository extends JpaRepository<DepositScores, Long>,JpaSpecificationExecutor<DepositScores>{

}

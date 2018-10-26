package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.RollCall;

public interface RollCallRepostory extends JpaRepository<RollCall, Long>,JpaSpecificationExecutor<RollCall>{

	
	
}

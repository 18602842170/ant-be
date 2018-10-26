package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Distinguish;

public interface DistinguishRepostory  extends JpaRepository<Distinguish, Long>,JpaSpecificationExecutor<Distinguish>{

}

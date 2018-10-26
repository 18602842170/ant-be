package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.CourseReserve;

public interface CourseReserveRepostory  extends JpaRepository<CourseReserve, Long>,JpaSpecificationExecutor<CourseReserve>{

}

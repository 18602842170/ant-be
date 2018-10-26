package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.CourseAllot;

public interface CourseAllotRepository extends JpaRepository<CourseAllot, Long>,JpaSpecificationExecutor<CourseAllot>{

}

package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.CourseDetail;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long>,JpaSpecificationExecutor<CourseDetail>{

}

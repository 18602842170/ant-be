package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Course;

public interface CourseRepostory extends JpaRepository<Course, Long>,JpaSpecificationExecutor<Course>{

}

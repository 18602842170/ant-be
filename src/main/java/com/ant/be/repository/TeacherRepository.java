package com.ant.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>,JpaSpecificationExecutor<Teacher>{

}

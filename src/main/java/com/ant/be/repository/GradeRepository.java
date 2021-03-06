package com.ant.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Grade;
import com.ant.be.entity.Student;
/**
 * 类似于表的服务,直接操作表
 * @author xujianxia
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Long>,JpaSpecificationExecutor<Grade>{

}

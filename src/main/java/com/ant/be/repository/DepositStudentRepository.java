package com.ant.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.DepositStudent;
import com.ant.be.entity.Teacher;

public interface DepositStudentRepository extends JpaRepository<DepositStudent, Long>,JpaSpecificationExecutor<DepositStudent>{

}

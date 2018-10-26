package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>,JpaSpecificationExecutor<Department>{

}

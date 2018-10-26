package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>,JpaSpecificationExecutor<Role>{
}

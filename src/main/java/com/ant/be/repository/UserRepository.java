package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>,JpaSpecificationExecutor<Users>{

}

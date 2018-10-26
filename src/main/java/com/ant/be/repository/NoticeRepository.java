package com.ant.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ant.be.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>,JpaSpecificationExecutor<Notice>{

}

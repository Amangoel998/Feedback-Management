package com.cg.feedback.dao;

import com.cg.feedback.dto.AdminDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<AdminDTO, String>{
	
}

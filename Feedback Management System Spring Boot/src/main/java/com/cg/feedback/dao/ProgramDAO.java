package com.cg.feedback.dao;

import java.util.List;

import com.cg.feedback.dto.ProgramDefaulters;
import com.cg.feedback.dto.ProgramDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramDAO extends JpaRepository<ProgramDTO,String>{
	List<ProgramDefaulters> findDefaulters(String id) throws Exception;
}

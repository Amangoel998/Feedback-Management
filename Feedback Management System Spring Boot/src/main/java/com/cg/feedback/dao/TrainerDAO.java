package com.cg.feedback.dao;

import java.util.List;

import com.cg.feedback.dto.TrainerDTO;
import com.cg.feedback.dto.TrainerDefaulters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TrainerDAO extends JpaRepository<TrainerDTO, String> {
	@Modifying
	void updateTrainerSkills(String skills,String id);
	List<TrainerDefaulters> findDefaulters(String id);
}
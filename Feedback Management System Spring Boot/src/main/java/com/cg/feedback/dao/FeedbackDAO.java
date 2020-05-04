package com.cg.feedback.dao;

import java.util.List;

import com.cg.feedback.dto.FeedbackDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDAO extends JpaRepository<FeedbackDTO,Integer> {
	List<FeedbackDTO> findAllByProgramId(String programId);
	List<FeedbackDTO> findAllByTrainerId(String trainerId);
}

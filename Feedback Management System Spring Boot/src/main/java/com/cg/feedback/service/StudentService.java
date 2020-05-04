package com.cg.feedback.service;

import java.util.List;

import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.exceptions.CustomException;

public interface StudentService{
	FeedbackDTO giveFeedback(FeedbackDTO feedback) throws CustomException;
	List<AvailableProgramsDTO> getAvailablePrograms(String studentId) throws CustomException;
}

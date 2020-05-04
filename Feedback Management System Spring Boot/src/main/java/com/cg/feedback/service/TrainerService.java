package com.cg.feedback.service;

import java.util.List;
import com.cg.feedback.dto.ReportDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.exceptions.CustomException;

public interface TrainerService{
	ReportDTO showFeedbackReport(String trainerId) throws CustomException;
	List<TrainerDefaulters> showDefaulters(String trainerId) throws CustomException;
}
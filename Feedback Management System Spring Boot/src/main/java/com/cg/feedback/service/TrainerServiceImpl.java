package com.cg.feedback.service;

import java.util.List;

import com.cg.feedback.dao.FeedbackDAO;
import com.cg.feedback.dao.TrainerDAO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.dto.ReportDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private FeedbackDAO fdbDao;
    private TrainerDAO trnDao;

    @Autowired
    public TrainerServiceImpl(FeedbackDAO fdbDao, TrainerDAO trnDao) {
        this.fdbDao = fdbDao;
        this.trnDao = trnDao;
    }

    @Override
    public ReportDTO showFeedbackReport(String trainerId) throws CustomException {
        try {
            if (trnDao.existsById(trainerId)) {
                List<FeedbackDTO> feedbacks = fdbDao.findAllByTrainerId(trainerId);
                if(feedbacks==null || feedbacks.size()<1)
                    throw new CustomException("No Feedbacks for the Trainer");
                ReportDTO result = new ReportDTO(feedbacks);
                return result;
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public List<TrainerDefaulters> showDefaulters(String trainerId) throws CustomException {
        try {
            if (trnDao.existsById(trainerId)) {
                List<TrainerDefaulters> defaulters = trnDao.findDefaulters(trainerId);
                if(defaulters==null || defaulters.size()<1)
                    throw new CustomException("No Feedbacks Defaulters for the Trainer");
                return defaulters;
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
    
}
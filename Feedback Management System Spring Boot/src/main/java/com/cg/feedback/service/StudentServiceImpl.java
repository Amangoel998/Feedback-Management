package com.cg.feedback.service;

import java.util.List;

import com.cg.feedback.dao.FeedbackDAO;
import com.cg.feedback.dao.ProgramDAO;
import com.cg.feedback.dao.StudentDAO;
import com.cg.feedback.dao.TrainerDAO;
import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO stdDao;
    private FeedbackDAO fdbDao;
    private TrainerDAO trnDao;
    private ProgramDAO prgDao;

    @Autowired
    public StudentServiceImpl(StudentDAO stdDao, FeedbackDAO fdbDao, TrainerDAO trnDao, ProgramDAO prgDao) {
        this.stdDao = stdDao;
        this.fdbDao = fdbDao;
        this.prgDao = prgDao;
        this.trnDao = trnDao;
    }

    @Override
    public FeedbackDTO giveFeedback(FeedbackDTO feedback) throws CustomException {
        try {
            if (feedback.getStudentId() == null || !stdDao.existsById(feedback.getStudentId()))
                throw new CustomException("Student with given ID Doesn't Exists");
            if (feedback.getProgramId() == null || !prgDao.existsById(feedback.getProgramId()))
                throw new CustomException("Program with given ID Doesn't Exists");
            if (feedback.getTrainerId() == null || !trnDao.existsById(feedback.getTrainerId()))
                throw new CustomException("Trainer with given ID Doesn't Exists");
            System.out.println(feedback);
            FeedbackDTO result = fdbDao.save(feedback);
            if (result == null)
                throw new CustomException("Couldn't Add Feedback");
            else
                return result;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<AvailableProgramsDTO> getAvailablePrograms(String studentId) throws CustomException {
        try {
            if (stdDao.existsById(studentId)) {
                List<AvailableProgramsDTO> result = stdDao.getAvailablePrograms(studentId);
                if (result == null || result.size() < 1)
                    throw new CustomException("Student is not enrolled in Any Program");
                else
                    return result;
            } else
                throw new CustomException("Student with given Id Doesn't Exists");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}
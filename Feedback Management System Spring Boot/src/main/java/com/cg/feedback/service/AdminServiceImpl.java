package com.cg.feedback.service;

import java.util.List;

import com.cg.feedback.dao.BatchDAO;
import com.cg.feedback.dao.CourseDAO;
import com.cg.feedback.dao.FeedbackDAO;
import com.cg.feedback.dao.LoginDAO;
import com.cg.feedback.dao.ProgramCourseDAO;
import com.cg.feedback.dao.ProgramDAO;
import com.cg.feedback.dao.StudentDAO;
import com.cg.feedback.dao.TrainerDAO;
import com.cg.feedback.dao.TrainerProgramDAO;
import com.cg.feedback.dto.BatchCourseDTO;
import com.cg.feedback.dto.CourseDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.dto.ProgramCourseDTO;
import com.cg.feedback.dto.ProgramDTO;
import com.cg.feedback.dto.ProgramDefaulters;
import com.cg.feedback.dto.ReportDTO;
import com.cg.feedback.dto.StudentDTO;
import com.cg.feedback.dto.TrainerDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.dto.TrainerProgramDTO;
import com.cg.feedback.dto.TrainerSkillDTO;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.id.ProgramCourseId;
import com.cg.feedback.id.ProgramTrainerId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FeedbackDAO fdbDao;
    private CourseDAO crsDao;
    private ProgramDAO prgDao;
    private StudentDAO stdDao;
    private TrainerDAO trnDao;
    private BatchDAO batDao;
    private ProgramCourseDAO prgcrsDao;
    private TrainerProgramDAO trnprgDao;
    private LoginDAO loginDao;

    @Autowired
    public AdminServiceImpl(LoginDAO loginDao,ProgramDAO prgDao, StudentDAO stdDao, TrainerDAO trnDao, CourseDAO crsDao, BatchDAO batDao,
            FeedbackDAO fdbDao, ProgramCourseDAO prgcrsDao, TrainerProgramDAO trnprgDao) {
        this.prgDao = prgDao;
        this.stdDao = stdDao;
        this.trnDao = trnDao;
        this.crsDao = crsDao;
        this.batDao = batDao;
        this.fdbDao = fdbDao;
        this.prgcrsDao = prgcrsDao;
        this.trnprgDao = trnprgDao;
        this.loginDao = loginDao;
    }

    @Override
    public ProgramDTO addProgram(ProgramDTO program) throws CustomException {
        try {
            if (prgDao.existsById(program.getProgramId()))
                throw new CustomException("Program with given Id Already Exists");
            ProgramDTO result = prgDao.save(program);
            if (result == null)
                throw new CustomException("Couldn't Add Program");
            else
                return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public StudentDTO addStudent(StudentDTO student) throws CustomException {
        try {
            if (stdDao.existsById(student.getStudentId()))
                throw new CustomException("Student with given Id Already Exists");
            StudentDTO result = stdDao.save(student);
            if (result == null)
                throw new CustomException("Couldn't Add Student");
            else
                return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public TrainerDTO addTrainer(TrainerDTO trainer) throws CustomException {
        try {
            if (trnDao.existsById(trainer.getTrainerId()))
                throw new CustomException("Trainer with given Id Already Exists");
            TrainerDTO result = trnDao.save(trainer);
            if (result == null)
                throw new CustomException("Couldn't Add Trainer");
            else
                return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public CourseDTO addCourse(CourseDTO course) throws CustomException {
        try {
            if (crsDao.existsById(course.getCourseId()) || course.getCourseName().isEmpty())
                throw new CustomException("Course with given Id Already Exists");
            CourseDTO result = crsDao.save(course);
            if (result == null)
                throw new CustomException("Couldn;t Add the Course");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public String updateTrainerSkill(TrainerSkillDTO trainerSkills) throws CustomException {
        try {
            if (!trnDao.existsById(trainerSkills.getTrainerId()))
                throw new CustomException("Trainer Not Exists");
            else if (trainerSkills.getSkills().isEmpty())
                throw new CustomException("Skills Provided is Empty");
            else
                trnDao.updateTrainerSkills(trainerSkills.getSkills(), trainerSkills.getTrainerId());
            return trainerSkills.getSkills();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public BatchCourseDTO addBatch(BatchCourseDTO batch) throws CustomException {
        try {
            BatchCourseDTO result = batDao.save(batch);
            if (result == null)
                throw new CustomException("Couldn't Add Batch");
            else
                return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeStudent(String studentId) throws CustomException {
        try {
            if (stdDao.existsById(studentId)) {
                stdDao.deleteById(studentId);
                loginDao.deleteById(studentId);
                return true;
            } else
                throw new CustomException("Student Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeBatch(String batch) throws CustomException {
        try {
            if (batDao.existsById(batch)) {
                batDao.deleteById(batch);
                return true;
            } else
                throw new CustomException("Batch Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }
    @Override
    public boolean removeProgram(String programId) throws CustomException {
        try {
            if (prgDao.existsById(programId)) {
                prgDao.deleteById(programId);
                prgcrsDao.removeProgram(programId);
                trnprgDao.removeProgram(programId);
                return true;
            } else
                throw new CustomException("Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeTrainer(String trainerId) throws CustomException {
        try {
            if (trnDao.existsById(trainerId)) {
                trnDao.deleteById(trainerId);
                trnprgDao.removeTrainer(trainerId);
                loginDao.deleteById(trainerId);
                return true;
            } else
                throw new CustomException("Student Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeCourse(String courseId) throws CustomException {
        try {
            if (crsDao.existsById(courseId)) {
                crsDao.deleteById(courseId);
                prgcrsDao.removeCourse(courseId);
                return true;
            } else
                throw new CustomException("Student Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<CourseDTO> getCourses() throws CustomException {
        try {
            List<CourseDTO> result = crsDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<TrainerDTO> getTrainers() throws CustomException {
        try {
            List<TrainerDTO> result = trnDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<StudentDTO> getStudents() throws CustomException {
        try {
            List<StudentDTO> result = stdDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<ProgramDTO> getPrograms() throws CustomException {
        try {
            List<ProgramDTO> result = prgDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<BatchCourseDTO> getBatches() throws CustomException {
        try {
            List<BatchCourseDTO> result = batDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<ProgramCourseDTO> getCoursePrograms() throws CustomException {
        try {
            List<ProgramCourseDTO> result = prgcrsDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<TrainerProgramDTO> getProgramTrainers() throws CustomException {
        try {
            List<TrainerProgramDTO> result = trnprgDao.findAll();
            if (result == null || result.size() < 1)
                throw new CustomException("Programs Not Found");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ReportDTO viewFeedbackByProgram(String programId) throws CustomException {
        try {
            if (prgDao.existsById(programId)) {
                List<FeedbackDTO> feedbacks = fdbDao.findAllByProgramId(programId);
                ReportDTO result = new ReportDTO(feedbacks);
                return result;
            } else
                throw new CustomException("The Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ReportDTO viewFeedbackByTrainer(String trainerId) throws CustomException {
        try {
            if (trnDao.existsById(trainerId)) {
                List<FeedbackDTO> feedbacks = fdbDao.findAllByTrainerId(trainerId);
                ReportDTO result = new ReportDTO(feedbacks);
                return result;
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<ProgramDefaulters> viewDefaultersByProgram(String programId) throws CustomException {
        try {
            if (prgDao.existsById(programId)) {
                List<ProgramDefaulters> defaulters = prgDao.findDefaulters(programId);
                if (defaulters.size() > 1)
                    return defaulters;
                else
                    throw new CustomException("No Defaulters found for the Program");
            } else
                throw new CustomException("The Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<TrainerDefaulters> viewDefaultersByTrainer(String trainerId) throws CustomException {
        try {
            if (trnDao.existsById(trainerId)) {
                List<TrainerDefaulters> defaulters = trnDao.findDefaulters(trainerId);
                if (defaulters.size() > 1)
                    return defaulters;
                else
                    throw new CustomException("No Defaulters found for the Trainer");
            } else
                throw new CustomException("Trainer Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ProgramCourseDTO addCoursePrograms(ProgramCourseDTO courseprogram) throws CustomException {
        try {
            if (prgcrsDao.existsById(courseprogram.getprogramCourseId()))
                throw new CustomException("Program in Course Already Exists");
            ProgramCourseDTO result = prgcrsDao.save(courseprogram);
            if (result == null)
                throw new CustomException("Couldn't Add Course Program");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public TrainerProgramDTO addProgramTrainers(TrainerProgramDTO trainerprogram) throws CustomException {
        try {
            if (trnprgDao.existsById(trainerprogram.getTrainerProgramId()))
                throw new CustomException("Program in Course Already Exists");
            TrainerProgramDTO result = trnprgDao.save(trainerprogram);
            if (result == null)
                throw new CustomException("Couldn't Add Course Program");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeProgramCourse(ProgramCourseId programcourseId) throws CustomException {
        try {
            if (prgcrsDao.existsById(programcourseId)) {
                prgcrsDao.deleteById(programcourseId);
                return true;
            } else
                throw new CustomException("Program in Course Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public boolean removeTrainerProgram(ProgramTrainerId trainerprogramId) throws CustomException {
        try {
            if (trnprgDao.existsById(trainerprogramId)) {
                trnprgDao.deleteById(trainerprogramId);
                return true;
            } else
                throw new CustomException("Trainer in Program Doesn't Exists");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CustomException(e.getMessage());
        }
    }

}
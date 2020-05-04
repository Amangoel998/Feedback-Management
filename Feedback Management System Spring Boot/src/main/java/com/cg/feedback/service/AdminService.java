package com.cg.feedback.service;

import java.util.List;
import com.cg.feedback.dto.BatchCourseDTO;
import com.cg.feedback.dto.CourseDTO;
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

public interface AdminService{

	ProgramDTO addProgram(ProgramDTO program)  throws CustomException;
	StudentDTO addStudent(StudentDTO student) throws CustomException;
	TrainerDTO addTrainer(TrainerDTO trainer) throws CustomException;
	CourseDTO addCourse(CourseDTO course) throws CustomException;
	BatchCourseDTO addBatch(BatchCourseDTO batches) throws CustomException;

	String updateTrainerSkill(TrainerSkillDTO trainerSkill) throws CustomException;

	ProgramCourseDTO addCoursePrograms(ProgramCourseDTO list) throws CustomException;
	TrainerProgramDTO addProgramTrainers(TrainerProgramDTO trainer) throws CustomException;
	

	boolean removeStudent(String studentID) throws CustomException;
	boolean removeProgram(String programId)  throws CustomException;
	boolean removeTrainer(String trainerID) throws CustomException;
	boolean removeCourse(String courseId) throws CustomException;
	boolean removeBatch(String batch) throws CustomException;

	boolean removeProgramCourse(ProgramCourseId programcourseId) throws CustomException;
	boolean removeTrainerProgram(ProgramTrainerId programCourseId) throws CustomException;
	
	ReportDTO viewFeedbackByProgram(String program) throws CustomException;
	ReportDTO viewFeedbackByTrainer(String trainer) throws CustomException;
	
	List<ProgramDefaulters> viewDefaultersByProgram(String program) throws CustomException;
	List<TrainerDefaulters> viewDefaultersByTrainer(String trainer) throws CustomException;
	
	List<CourseDTO> getCourses() throws CustomException;
	List<TrainerDTO> getTrainers() throws CustomException;
	List<StudentDTO> getStudents() throws CustomException;
	List<ProgramDTO> getPrograms() throws CustomException;
	List<BatchCourseDTO> getBatches() throws CustomException;

	List<ProgramCourseDTO> getCoursePrograms() throws CustomException;
	List<TrainerProgramDTO> getProgramTrainers() throws CustomException;
	
	
}

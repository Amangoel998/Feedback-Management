package com.cg.feedback.restcontollers;

import java.util.List;
import java.util.StringTokenizer;

import com.cg.feedback.dto.BatchCourseDTO;
import com.cg.feedback.dto.CourseDTO;
import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.LoginResponse;
import com.cg.feedback.dto.NewStudentDTO;
import com.cg.feedback.dto.NewTrainerDTO;
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
import com.cg.feedback.service.AdminService;
import com.cg.feedback.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api/admin")
public class AdminContoller {

    private AdminService admService;
    private UserService loginService;

    @Autowired
    public AdminContoller(AdminService admService, UserService loginService) throws CustomException {
        this.admService = admService;
        this.loginService = loginService;
    }

    // ------------------ Admin Requests---------------------
    // ----------View Feedback Reports-------------
    @GetMapping(value = "/programReport", produces = "application/json")
    public ResponseEntity<ReportDTO> getProgramFeedback(@RequestParam("programId") String programId)
            throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        ReportDTO result = admService.viewFeedbackByProgram(programId);
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/trainerReport", produces = "application/json")
    public ResponseEntity<ReportDTO> getTrainerFeedback(@RequestParam("trainerId") String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        ReportDTO result = admService.viewFeedbackByTrainer(trainerId);
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }
    // ---------------View Feedback Defaulters----------------
    @GetMapping(value = "/programFeedbackDefaulters", produces = "application/json")
    public ResponseEntity<List<ProgramDefaulters>> getDefaultersByProgram(@RequestParam("programId") String programId)
            throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        List<ProgramDefaulters> result = admService.viewDefaultersByProgram(programId);
        return new ResponseEntity<List<ProgramDefaulters>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/trainerFeedbackDefaulters", produces = "application/json")
    public ResponseEntity<List<TrainerDefaulters>> getDefaultersByTrainer(@RequestParam("trainerId") String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        List<TrainerDefaulters> result = admService.viewDefaultersByTrainer(trainerId);
        return new ResponseEntity<List<TrainerDefaulters>>(result, HttpStatus.OK);
    }
    // ---------------Get list of Items----------------
    @GetMapping(value = "/batches", produces = "application/json")
    public ResponseEntity<List<BatchCourseDTO>> getBatches() throws CustomException {
        List<BatchCourseDTO> result = admService.getBatches();
        return new ResponseEntity<List<BatchCourseDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/courses", produces = "application/json")
    public ResponseEntity<List<CourseDTO>> getCourses() throws CustomException {
        List<CourseDTO> result = admService.getCourses();
        return new ResponseEntity<List<CourseDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/programs", produces = "application/json")
    public ResponseEntity<List<ProgramDTO>> getPrograms() throws CustomException {
        List<ProgramDTO> result = admService.getPrograms();
        return new ResponseEntity<List<ProgramDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/trainers", produces = "application/json")
    public ResponseEntity<List<TrainerDTO>> getTrainers() throws CustomException {
        List<TrainerDTO> result = admService.getTrainers();
        return new ResponseEntity<List<TrainerDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/students", produces = "application/json")
    public ResponseEntity<List<StudentDTO>> getStudents() throws CustomException {
        List<StudentDTO> result = admService.getStudents();
        return new ResponseEntity<List<StudentDTO>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/coursePrograms", produces = "application/json")
    public ResponseEntity<List<ProgramCourseDTO>> getCoursePrograms() throws CustomException {

        List<ProgramCourseDTO> result = admService.getCoursePrograms();
        return new ResponseEntity<List<ProgramCourseDTO>>(result, HttpStatus.OK);
    }
    
    @GetMapping(value = "/programTrainers", produces = "application/json")
    public ResponseEntity<List<TrainerProgramDTO>> getProgramTrainers() throws CustomException {
        
        List<TrainerProgramDTO> result = admService.getProgramTrainers();
        return new ResponseEntity<List<TrainerProgramDTO>>(result, HttpStatus.OK);
    }
    // ----------------Admin POST methods-------------------
    
    @PostMapping(value = "/addBatch", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BatchCourseDTO> addBatch(@RequestBody BatchCourseDTO batch) throws CustomException {
        if (batch == null)
            throw new CustomException("Invalid Request");
        BatchCourseDTO result = admService.addBatch(batch);
        return new ResponseEntity<BatchCourseDTO>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addProgram", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProgramDTO> addProgram(@RequestBody ProgramDTO program) throws CustomException {
        if (program == null)
            throw new CustomException("Invalid Request");
        ProgramDTO result = admService.addProgram(program);
        return new ResponseEntity<ProgramDTO>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addCourse", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) throws CustomException {
        if (course == null)
            throw new CustomException("Invalid Request");
        CourseDTO result = admService.addCourse(course);
        return new ResponseEntity<CourseDTO>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTrainer", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody NewTrainerDTO trainer) throws CustomException {
        if (trainer == null)
            throw new CustomException("Invalid Request");
        TrainerDTO trn = new TrainerDTO(trainer);
        trn = admService.addTrainer(trn);
        if(trn == null)
            throw new CustomException("Couldn't Create new Trainer");
        LoginCredentials crednetials = new LoginCredentials(trainer.getTrainerId(), trainer.getTrainerPass(), "trainer");
        LoginResponse log = loginService.createUser(crednetials);
        if(log!=null)
            throw new CustomException("Trainer created but Couldn't create Login");
        return new ResponseEntity<TrainerDTO>(trn, HttpStatus.OK);
    }

    @PostMapping(value = "/addStudent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody NewStudentDTO student) throws CustomException {
        if (student == null)
            throw new CustomException("Invalid Request");
        StudentDTO std = new StudentDTO(student);
        std = admService.addStudent(std);
        if (std == null)
            throw new CustomException("Couldn't Create new Student");
        LoginCredentials newLogin = new LoginCredentials(student.getStudentId(), student.getStudentPass(), "student");
        LoginResponse login = loginService.createUser(newLogin);
        if (login == null)
            throw new CustomException("Student created but couldn't create its login");
        return new ResponseEntity<StudentDTO>(std, HttpStatus.OK);
    }

    // ----------Add Program to Course----------------
    @PostMapping(value = "/addProgramToCourse", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProgramCourseDTO> assignCoursePrograms(@RequestBody ProgramCourseDTO courseprogram)
            throws CustomException {
        if (courseprogram == null)
            throw new CustomException("Invalid Request");
        ProgramCourseDTO result = admService.addCoursePrograms(courseprogram);
        return new ResponseEntity<ProgramCourseDTO>(result, HttpStatus.OK);
    }
    // -------------Add Trainer to Program
    @PostMapping(value = "/addTrainerToProgram", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TrainerProgramDTO> assignProgramTrainers(@RequestBody TrainerProgramDTO programtrainer)
            throws CustomException {
        if (programtrainer == null)
            throw new CustomException("Invalid Request");
        TrainerProgramDTO result = admService.addProgramTrainers(programtrainer);
        return new ResponseEntity<TrainerProgramDTO>(result, HttpStatus.OK);
    }

    // -------------PUT Trainer Skill
    @PutMapping(value = "/maintainTrainerSkills", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> updateTrainerSkill(@RequestBody TrainerSkillDTO trainerSkills)
            throws CustomException {
        if (trainerSkills == null)
            throw new CustomException("Invalid Request");
        String result = admService.updateTrainerSkill(trainerSkills);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    // ---------------Admin Delete Methods--------------------
    @DeleteMapping(value = "/removeStudent", produces = "application/json")
    public ResponseEntity<String> removeStudent(@RequestParam("studentId") String studentId)
            throws CustomException {
        if (studentId == null || studentId.isEmpty())
            throw new CustomException("Invalid Request");
        admService.removeStudent(studentId);
        return new ResponseEntity<String>("Student Deleted", HttpStatus.OK);
    }
    @DeleteMapping(value = "/removeBatch", produces = "application/json")
    public ResponseEntity<String> removeBatch(@RequestParam("batch") String batch)
            throws CustomException {
        if (batch == null || batch.isEmpty())
            throw new CustomException("Invalid Request");
        admService.removeBatch(batch);
        return new ResponseEntity<String>("Batch Deleted", HttpStatus.OK);
    }
    @DeleteMapping(value = "/removeProgram", produces = "application/json")
    public ResponseEntity<String> removeProgram(@RequestParam("programId") String programId)
            throws CustomException {
        if (programId == null || programId.isEmpty())
            throw new CustomException("Invalid Request");
        admService.removeProgram(programId);
        return new ResponseEntity<String>("Program Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeTrainer", produces = "application/json")
    public ResponseEntity<String> removeTrainer(@RequestParam("trainerId") String trainerId)
            throws CustomException {
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        admService.removeTrainer(trainerId);
        return new ResponseEntity<String>("Trainer Deleted",HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeCourse", produces = "application/json")
    public ResponseEntity<String> removeCourse(@RequestParam("courseId") String courseId)
            throws CustomException {
        if (courseId == null || courseId.isEmpty())
            throw new CustomException("Invalid Request");
        admService.removeCourse(courseId);
        return new ResponseEntity<String>("Course Deleted", HttpStatus.OK);
    }
    @DeleteMapping(value = "/removeProgramFromCourse", produces = "application/json")
    public ResponseEntity<String> removeProgramInCourse(@RequestParam("programcourseId") String programcourseId)
            throws CustomException {
        if (programcourseId == null || programcourseId.isEmpty())
            throw new CustomException("Invalid Request");
        StringTokenizer stkr = new StringTokenizer(programcourseId, "-");
        admService.removeProgramCourse(new ProgramCourseId(stkr.nextToken(), stkr.nextToken()));
        return new ResponseEntity<String>("Program removed from Course", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeTrainerFromProgram", produces = "application/json")
    public ResponseEntity<String> removeTrainerInProgram(@RequestParam("trainerprogramId") String trainerprogramId)
            throws CustomException {
        if (trainerprogramId == null || trainerprogramId.isEmpty())
            throw new CustomException("Invalid Request");
            
        StringTokenizer stkr = new StringTokenizer(trainerprogramId, "-");
        admService.removeTrainerProgram(new ProgramTrainerId(stkr.nextToken(), stkr.nextToken(), stkr.nextToken()));
        return new ResponseEntity<String>("Trainer removed from Program", HttpStatus.OK);
    }
}
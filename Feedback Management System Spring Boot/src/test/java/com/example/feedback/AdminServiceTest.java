package com.example.feedback;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;

import com.cg.feedback.SpringFeedbackApplication;
import com.cg.feedback.dto.BatchCourseDTO;
import com.cg.feedback.dto.CourseDTO;
import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.LoginResponse;
import com.cg.feedback.dto.NewTrainerDTO;
import com.cg.feedback.dto.ProgramCourseDTO;
import com.cg.feedback.dto.ProgramDefaulters;
import com.cg.feedback.dto.StudentDTO;
import com.cg.feedback.dto.TrainerDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.AdminService;
import com.cg.feedback.service.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes=SpringFeedbackApplication.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Autowired
    private AdminService admService;

    @Autowired
    private UserService usrService;

    @Test
    public void testUserAuth(){
        LoginResponse result = usrService.AuthenticateUser(new LoginCredentials("ADM101", "Admin@123", null));
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void getFunctions(){
        List<StudentDTO> students = admService.getStudents();
        System.out.println(students);
        Assert.assertNotNull(students);

        List<BatchCourseDTO> result = admService.getBatches();
        System.out.println(result);
        Assert.assertNotNull(result);
    }
    @Test
    public void deleteOperations(){
        boolean result = admService.removeStudent("STD101");
        System.out.println(result);
        Assert.assertTrue("Deleted Student", result);

        result = admService.removeStudent("STD101");
        System.out.println(result);
        Assert.assertFalse("Student Not Found", result);
    }
    @Test(expected = CustomException.class)
    public void deleteExceptions(){
        boolean result = admService.removeStudent("STD101");
        System.out.println(result);
        result = admService.removeTrainer("TRN109");
        System.out.println(result);
    }
    @Test
    public void updateSkills(){
        TrainerDTO newtrn = new TrainerDTO("TRN105", "Deepak Gupta", "Physics, Mechanics, Physical Dynamics", "deepak@swarswatiacademy.com");
        TrainerDTO trn = admService.addTrainer(newtrn);
        System.out.println(trn);
        Assert.assertNotNull(trn);
    }
    @Test
    public void checkDefaulters(){
        List<TrainerDefaulters> trndef = admService.viewDefaultersByTrainer("TRN101");
        System.out.println(trndef.size());
        assertNotNull(trndef);

        List<ProgramDefaulters> prgdef = admService.viewDefaultersByProgram("PRG101");
        System.out.println(prgdef.size());
        assertNotNull(prgdef);
    }

    @Test
    public void addCourse(){
        CourseDTO input = new CourseDTO("JavaScript Full Stack", "CRS108");
        CourseDTO courses = admService.addCourse(input);
        System.out.println(courses);
        assertNotNull(courses);
    }
    @Test
    public void checkPRogramCourse(){
        List<ProgramCourseDTO> list = new ArrayList<>();
        list.add(new ProgramCourseDTO());
        list.add(new ProgramCourseDTO());
        // ProgramCourseDTO res = admService.addCoursePrograms(list);
        // System.out.println(res);
        // Assert.assertNotNull(res);
    }
    @Test
    public void trainerTest(){
        NewTrainerDTO trainer = new NewTrainerDTO("STD105", "Gaurav Ahuja", "Student@105", "gauravahuja@capgemini.com", "Angular, React, Node.js");
        // TrainerDTO trn = new TrainerDTO(trainer);
        // trn = admService.addTrainer(trn);
        // Assert.assertNotNull(trn);
        // System.out.println(trn);
        LoginCredentials crednetials = new LoginCredentials(trainer.getTrainerId(), trainer.getTrainerPass(), "student");
        LoginResponse log = usrService.createUser(crednetials);
        System.out.println(log);
        Assert.assertNotNull(log);
    }
}
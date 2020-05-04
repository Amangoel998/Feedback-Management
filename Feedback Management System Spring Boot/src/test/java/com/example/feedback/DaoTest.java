package com.example.feedback;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cg.feedback.SpringFeedbackApplication;
import com.cg.feedback.dao.AdminDAO;
import com.cg.feedback.dao.BatchDAO;
import com.cg.feedback.dao.CourseDAO;
import com.cg.feedback.dao.FeedbackDAO;
import com.cg.feedback.dao.LoginDAO;
import com.cg.feedback.dao.ProgramCourseDAO;
import com.cg.feedback.dao.ProgramDAO;
import com.cg.feedback.dao.StudentDAO;
import com.cg.feedback.dao.TrainerDAO;
import com.cg.feedback.dto.AdminDTO;
import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.BatchCourseDTO;
import com.cg.feedback.dto.CourseDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.ProgramCourseDTO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringFeedbackApplication.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class DaoTest {

   @Autowired
   private AdminDAO admDao;
   @Autowired
   private StudentDAO stdDao;
   @Autowired
   private TrainerDAO trnDao;
   @Autowired
   private ProgramDAO prgDao;
   @Autowired
   private BatchDAO batDao;
   @Autowired
   private CourseDAO crsDao;
   @Autowired
   private FeedbackDAO fdbDao;
   @Autowired
   private ProgramCourseDAO prgcrsDao;

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   @Test
   public void checkAdmin() {
      AdminDTO admin = admDao.findById("ADM101").get();
      System.out.println(admin);
      Assert.assertNotNull(admin);
   }

   @Test
   @Transactional
   public void checkTrainer() {
      // Assert.assertTrue(trnDao.existsById("TRN102"));
      // TrainerDTO trn = new TrainerDTO();
      // TrainerDTO res = trnDao.save(trn);
      // System.out.println(res);
      // Assert.assertNull(res);

      trnDao.updateTrainerSkills("Node.js, Angular JS", "TRN103");
      System.out.println("Done");
      Assert.assertNotNull("Done");
   }

   @Test
   public void checkStudent() {
      // Assert.assertNotNull(stdDao.validateStudent("STD101", "student101"));
      // Assert.assertNotNull(stdDao.findById("STD103"));
      // Assert.assertNotNull(stdDao.findAll());
      // StudentDTO newStudent = new StudentDTO("STD105", "Ashish Verma",
      // "student105", "ashish.verma@gmail.com","A");
      // Assert.assertNotNull(stdDao.save(newStudent));
      List<AvailableProgramsDTO> res = stdDao.getAvailablePrograms("STD104");
      Assert.assertNotNull(res);
      System.out.println(res.get(0));
      Assert.assertTrue(stdDao.getAvailablePrograms("STD102").size() == 0);
   }

   @Test
   public void checkProgram() {
      Assert.assertTrue(prgDao.existsById("PRG101"));
   }

   @Test
   public void checkFeedback() {
      List<FeedbackDTO> res = fdbDao.findAllByProgramId("PRG103");
      Assert.assertNotNull(res);
      System.out.println(res);
      res = fdbDao.findAllByTrainerId("TRN104");
      System.out.println(res);
      Assert.assertNotNull(res);
   }

   @Test
   public void checkBatch() {
      Optional<BatchCourseDTO> res = batDao.findById("A");
      Assert.assertNotNull(res);
      res = batDao.findById("C");
      Assert.assertTrue(res != null);
      Assert.assertNotNull(batDao.save(new BatchCourseDTO("C", "CRS103")));
   }

   @Test
   public void addCourse() {
      List<ProgramCourseDTO> courseprograms = new ArrayList<>();
      CourseDTO course = new CourseDTO("JavaScript Full Stack", "CRS108");
      courseprograms
            .add(new ProgramCourseDTO("CRS101", "PRG101", Date.valueOf("2020-03-24"), Date.valueOf("2020-04-12")));
      courseprograms
            .add(new ProgramCourseDTO("CRS101", "PRG102", Date.valueOf("2020-03-20"), Date.valueOf("2020-04-05")));
      CourseDTO result = crsDao.save(course);
      System.out.println(result);
      assertNotNull(result);
   }

   @Test
    public void checkPRogramCourse(){
        List<ProgramCourseDTO> list = new ArrayList<>();
        list.add(new ProgramCourseDTO("CRS101","PRG101", Date.valueOf("2020-03-24") ,Date.valueOf("2020-04-12")));
        list.add(new ProgramCourseDTO("CRS101","PRG102",Date.valueOf("2020-03-20") ,Date.valueOf("2020-04-05")));
        List<ProgramCourseDTO> res = prgcrsDao.saveAll(list);
        System.out.println(res);
        Assert.assertNotNull(res);
    }
    @Autowired
    private LoginDAO logDao;
    @Test
    public void checkLogin(){
        LoginCredentials cred = logDao.validateUser("STD101", "Student@101");
        System.out.println(cred);
        Assert.assertNotNull(cred);
    }

}
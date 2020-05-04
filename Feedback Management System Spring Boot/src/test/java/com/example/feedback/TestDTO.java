package com.example.feedback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cg.feedback.SpringFeedbackApplication;
import com.cg.feedback.dto.TrainerDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes=SpringFeedbackApplication.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
// @DataJpaTest
@Transactional
public class TestDTO {
   @PersistenceContext
   private EntityManager entityManager;

   private Logger logger = LoggerFactory.getLogger(this.getClass());
  
   @Test
   public void checkTrainer() {
        TrainerDTO trn1 = new TrainerDTO("TRN111", "Sahil Verma","Java, JavaEE, Spring" , "sahilverma@gmail.com");
        entityManager.persist(trn1);
        logger.debug(trn1.toString());
   }
   @Test
   public void testcourse(){
      
   }

}
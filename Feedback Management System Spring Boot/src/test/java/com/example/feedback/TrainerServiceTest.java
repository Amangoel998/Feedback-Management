package com.example.feedback;

import java.util.List;

import com.cg.feedback.SpringFeedbackApplication;
import com.cg.feedback.dto.ReportDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.TrainerService;

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
public class TrainerServiceTest {

    @Autowired
    private TrainerService trnService;

    @Test
    public void checkFeedbacks(){
        String std = "TRN104";
        ReportDTO res = trnService.showFeedbackReport(std);
        System.out.println(res);
        Assert.assertNotNull(res);
    }
    @Test()
    public void checkDefaulters(){
        List<TrainerDefaulters> res = trnService.showDefaulters("TRN104");
        System.out.println(res.size());
        Assert.assertTrue(res.size()>0);
    }
}
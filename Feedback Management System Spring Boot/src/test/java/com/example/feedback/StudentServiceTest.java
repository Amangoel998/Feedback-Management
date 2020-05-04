package com.example.feedback;

import java.util.List;

import com.cg.feedback.SpringFeedbackApplication;
import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.StudentService;

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
public class StudentServiceTest {

    @Autowired
    private StudentService stdService;

    @Test
    public void checkAddFeedback(){
        String std = "STD104";
        List<AvailableProgramsDTO> res = stdService.getAvailablePrograms(std);
        String prg = res.get(0).getProgramId();
        String trn = res.get(0).getTrainerId();
        System.out.println(prg+" "+trn);

        FeedbackDTO newfeed = new FeedbackDTO(std, trn, prg, 5, 2, 3, 1, 3, "Nice", "Couldn't be better");
        FeedbackDTO result = stdService.giveFeedback(newfeed);
        System.out.println(result);
        Assert.assertNotNull(result);
    }
    @Test(expected=CustomException.class)
    public void checkPrograms(){
        List<AvailableProgramsDTO> res = stdService.getAvailablePrograms("STD104");
        Assert.assertNotNull(res);
        System.out.println(res);
        Assert.assertTrue(stdService.getAvailablePrograms("STD102").size() == 0);
    }
}
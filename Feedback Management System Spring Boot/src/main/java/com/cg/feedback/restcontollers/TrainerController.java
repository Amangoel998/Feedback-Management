package com.cg.feedback.restcontollers;

import java.util.List;

import com.cg.feedback.dto.ReportDTO;
import com.cg.feedback.dto.TrainerDefaulters;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/trainer")
public class TrainerController {
    private final TrainerService trnService;

    @Autowired
    public TrainerController(final TrainerService trnService) throws CustomException{
        this.trnService = trnService;
    }
    // ------------------Trainer Methods--------------------
    
    @GetMapping(value = "/showFeedbackReport", produces = "application/json")
    public ResponseEntity<ReportDTO> showFeedbackReport(@RequestParam("trainerId") final String trainerId) throws CustomException{
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        final ReportDTO result = trnService.showFeedbackReport(trainerId);
        return new ResponseEntity<ReportDTO>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/showDefaulters", produces = "application/json")
    public ResponseEntity<List<TrainerDefaulters>> showDefaulters(@RequestParam("trainerId") final String trainerId) throws CustomException{
        if (trainerId == null || trainerId.isEmpty())
            throw new CustomException("Invalid Request");
        final List<TrainerDefaulters> result = trnService.showDefaulters(trainerId);
        return new ResponseEntity<List<TrainerDefaulters>>(result, HttpStatus.OK);
    }
}
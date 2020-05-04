package com.cg.feedback.restcontollers;

import java.util.List;

import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.FeedbackDTO;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/student")
public class StudentController {
    private StudentService stdService;

    @Autowired
    public StudentController(StudentService stdService) throws CustomException {
        this.stdService = stdService;
    }

    // ------------------Student Methods--------------------
    @GetMapping(value = "/programs", produces = "application/json")
    public ResponseEntity<List<AvailableProgramsDTO>> availablePrograms(@RequestParam("studentId") String studentId)
            throws CustomException {
        if (studentId == null || studentId.isEmpty())
            throw new CustomException("Invalid Request");
        List<AvailableProgramsDTO> result = stdService.getAvailablePrograms(studentId);
        return new ResponseEntity<List<AvailableProgramsDTO>>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/giveFeedback", produces = "application/json", consumes = "application/json")
    public ResponseEntity<FeedbackDTO> giveFeedback(@RequestBody FeedbackDTO feedback) throws CustomException {
        if (feedback == null)
            throw new CustomException("Invalid Request");
        FeedbackDTO result = stdService.giveFeedback(feedback);
        return new ResponseEntity<FeedbackDTO>(result, HttpStatus.OK);
    }
}
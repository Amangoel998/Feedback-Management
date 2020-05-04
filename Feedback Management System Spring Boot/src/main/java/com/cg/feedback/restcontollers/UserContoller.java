package com.cg.feedback.restcontollers;

import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.LoginResponse;
import com.cg.feedback.exceptions.CustomException;
import com.cg.feedback.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserContoller {
    private UserService loginService;

    @Autowired
    public UserContoller(UserService loginService) throws CustomException {
        this.loginService = loginService;
    }

    // ----------------User Login---------------------------
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginCredentials credentials) throws CustomException {
        if (credentials.getId() == null || credentials.getPassword() == null)
            throw new CustomException("Invalid Request");
        LoginResponse result = loginService.AuthenticateUser(credentials);
        System.out.println(result);
        return new ResponseEntity<LoginResponse>(result, HttpStatus.OK);

    }
}
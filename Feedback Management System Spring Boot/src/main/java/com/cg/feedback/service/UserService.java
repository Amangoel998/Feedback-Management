package com.cg.feedback.service;

import com.cg.feedback.dto.LoginCredentials;
import com.cg.feedback.dto.LoginResponse;
import com.cg.feedback.exceptions.CustomException;

public interface UserService {
    LoginResponse AuthenticateUser(LoginCredentials credentials) throws CustomException;
    LoginResponse createUser(LoginCredentials crednetials) throws CustomException;
}
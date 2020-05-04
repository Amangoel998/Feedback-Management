package com.cg.feedback.dao;

import com.cg.feedback.dto.LoginCredentials;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDAO extends JpaRepository<LoginCredentials, String> {
    LoginCredentials validateUser(String id, String pass);
}
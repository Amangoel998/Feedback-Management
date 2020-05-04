package com.cg.feedback.dao;

import com.cg.feedback.dto.BatchCourseDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchDAO extends JpaRepository<BatchCourseDTO, String>{
}
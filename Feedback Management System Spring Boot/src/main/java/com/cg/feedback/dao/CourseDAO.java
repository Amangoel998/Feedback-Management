package com.cg.feedback.dao;

import com.cg.feedback.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<CourseDTO, String>{

}

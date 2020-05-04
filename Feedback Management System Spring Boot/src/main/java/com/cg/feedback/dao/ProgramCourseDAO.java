package com.cg.feedback.dao;

import com.cg.feedback.dto.ProgramCourseDTO;
import com.cg.feedback.id.ProgramCourseId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ProgramCourseDAO extends JpaRepository<ProgramCourseDTO,ProgramCourseId>{
    @Modifying
    void removeProgram(String id) throws Exception;
    @Modifying
    void removeCourse(String id) throws Exception;
}
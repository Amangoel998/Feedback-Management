package com.cg.feedback.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.cg.feedback.dto.AvailableProgramsDTO;
import com.cg.feedback.dto.StudentDTO;

public interface StudentDAO extends JpaRepository<StudentDTO, String> {
	List<AvailableProgramsDTO> getAvailablePrograms(String id);
}

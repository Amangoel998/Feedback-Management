package com.cg.feedback.dao;

import com.cg.feedback.dto.TrainerProgramDTO;
import com.cg.feedback.id.ProgramTrainerId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TrainerProgramDAO extends JpaRepository<TrainerProgramDTO, ProgramTrainerId>{
    @Modifying
    void removeTrainer(String id) throws Exception;
    @Modifying
    void removeProgram(String id) throws Exception;
}
package com.cg.feedback.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.cg.feedback.id.ProgramTrainerId;

@Entity
@Table(name="program_trainers")
@NamedNativeQueries({
	@NamedNativeQuery(
		name="TrainerProgramDTO.removeProgram",
		query = "DELETE * FROM program_trainers WHERE program_id=:id"
	),
	@NamedNativeQuery(
		name="TrainerProgramDTO.removeTrainer",
		query = "DELETE * FROM program_trainers WHERE trainer_id=:id"
	)
})
public class TrainerProgramDTO implements Serializable{
	private static final long serialVersionUID = 4566907954458907989L;

	@EmbeddedId
	private ProgramTrainerId trainerProgramId;

	public TrainerProgramDTO() {
	}
	public TrainerProgramDTO(ProgramTrainerId trainerprogramId) {
		this.trainerProgramId = trainerprogramId;
	}

	public ProgramTrainerId getTrainerProgramId() {
		return trainerProgramId;
	}

	public void setTrainerProgramId(String trainerId, String programId, String batch) {
		this.trainerProgramId = new ProgramTrainerId(trainerId, programId,batch);
	}

	public void setTrainerProgramId(ProgramTrainerId trainerProgramId) {
		this.trainerProgramId = trainerProgramId;
	}
	
}

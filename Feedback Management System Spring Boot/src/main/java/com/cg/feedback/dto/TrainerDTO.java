package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity 
@Table(name = "trainers")
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "TrainerDTO.updateTrainerSkills", 
		query = "UPDATE trainers SET skills=:skills WHERE trainer_id=:id"
	),
	@NamedNativeQuery(
		name = "TrainerDTO.findDefaulters", 
		query = "SELECT batch AS batch, program_id AS programId, student_name AS studentName, student_id AS studentId, student_email AS studentEmail, start_date AS startDate, end_date AS endDate FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs USING(course_id) INNER JOIN program_trainers USING(program_id, batch) LEFT JOIN feedbacks USING(student_id,program_id, trainer_id) WHERE feedback_id IS NULL AND CURDATE()>DATE_ADD(end_date, INTERVAL 15 DAY) AND trainer_id=:id ORDER BY program_id ASC")
})
public class TrainerDTO {
	@Id
	@Column(name = "trainer_id")
	private String trainerId;

	@Column(name = "trainer_name")
	private String trainerName;

	@Column(name = "skills")
	private String trainerSkills;

	@Column(name = "trainer_email")
	private String trainerEmail;

	// constructor
	public TrainerDTO() {
		super();
	}
	public TrainerDTO(String trainerId, String trainerName, String skills, String trainerEmail) {
		super();
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.trainerSkills = skills;
		this.trainerEmail = trainerEmail;
	}
	public TrainerDTO(NewTrainerDTO trainer){
		super();
		this.trainerId = trainer.getTrainerId();
		this.trainerEmail = trainer.getTrainerEmail();
		this.trainerName = trainer.getTrainerName();
		this.trainerSkills = trainer.getTrainerSkills();
	}

	// Getters and Setters
	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerEmail() {
		return trainerEmail;
	}

	public void setTrainerEmail(String trainerEmail) {
		this.trainerEmail = trainerEmail;
	}

	public String getTrainerSkills() {
		return trainerSkills;
	}

	public void setTrainerSkills(String trainerSkills) {
		this.trainerSkills = trainerSkills;
	}

	@Override
	public boolean equals(Object obj) {
		TrainerDTO temp = (TrainerDTO) obj;
		if (temp.getTrainerEmail().equals(this.trainerEmail))
			return true;
		return false;
	}

}

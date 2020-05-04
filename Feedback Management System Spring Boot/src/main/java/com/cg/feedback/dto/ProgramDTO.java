package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="programs")
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "ProgramDTO.findDefaulters", 
		query = "SELECT batch AS batch, student_name AS studentName, student_id AS studentId, student_email AS studentEmail, start_date AS startDate, end_date AS endDate FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) left join feedbacks USING(student_id,program_id) WHERE feedback_id IS NULL AND CURDATE()>DATE_ADD(end_date, INTERVAL 20 DAY) AND program_id=:id ORDER BY batch ASC")
	})
public class ProgramDTO {

	@Id
	@Column(name="program_id")
	private String programId;
	
	@Column(name="program_name")
	private String programName;

	// Constructor
	public ProgramDTO(String programId, String programName) {
		super();
		this.programName = programName;
		this.programId = programId;
	}

	public ProgramDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ProgramDTO [programId=" + programId + ", programName=" + programName;
	}

	// Getters and Setters
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@Override
	public boolean equals(Object obj) {
		ProgramDTO temp = (ProgramDTO) obj;
		if (temp.getProgramName().equals(this.programName))
			return true;
		return false;
	}

}

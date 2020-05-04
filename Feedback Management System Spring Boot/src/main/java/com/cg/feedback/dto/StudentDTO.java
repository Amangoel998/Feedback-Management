package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "students")
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "StudentDTO.validateStudent",
		query = "SELECT * FROM Students s WHERE s.studentId=:id AND s.studentPass=:pass", resultClass = StudentDTO.class),
	@NamedNativeQuery(
		name = "StudentDTO.getAvailablePrograms",
		query = "SELECT p.program_id AS programId, p.program_name AS programName, t.trainer_id AS trainerId, t.trainer_name AS trainerName, cp.start_date AS startDate, cp.end_date AS endDate from students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) INNER JOIN program_trainers USING(program_id,batch) INNER JOIN programs p USING(program_id) INNER JOIN trainers t USING(trainer_id) LEFT JOIN feedbacks USING(student_id, program_id, trainer_id) WHERE feedback_id IS NULL AND student_id=:id"
	)
})

public class StudentDTO {

	@Id
	@Column(name = "student_id", updatable = false)
	private String studentId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_email")
	private String studentEmail;

	@Column(name = "batch")
	private String batch;

	// Constructor

	public StudentDTO() {
		super();
	}

	public StudentDTO(String studentId, String studentName, String studentEmail, String batch) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.batch = batch;
	}
	public StudentDTO(NewStudentDTO student) {
		super();
		this.batch = student.getBatch();
		this.studentEmail = student.getStudentEmail();
		this.studentId = student.getStudentId();
		this.studentName = student.getStudentName();
	}

	// getter and Setters

	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", studentPass="
				+ ", studentEmail=" + studentEmail + ", batch=" + batch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String student_name) {
		this.studentName = student_name;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
}

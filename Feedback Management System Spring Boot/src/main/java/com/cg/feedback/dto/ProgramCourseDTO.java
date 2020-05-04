package com.cg.feedback.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cg.feedback.id.ProgramCourseId;

@Entity
@Table(name = "course_programs")
@NamedNativeQueries({
	@NamedNativeQuery(
		name="ProgramCourseDTO.removeProgram",
		query = "DELETE * FROM course_programs WHERE program_id=:id"
	),
	@NamedNativeQuery(
		name="ProgramCourseDTO.removeCourse",
		query = "DELETE * FROM course_programs WHERE course_id=:id"
	)
})
public class ProgramCourseDTO implements Serializable {
	private static final long serialVersionUID = 2932724140469190108L;

	@EmbeddedId
	private ProgramCourseId programCourseId;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	public ProgramCourseDTO(String courseId, String programId, Date startdate, Date enddate) {
		super();
		this.programCourseId = new ProgramCourseId(courseId, programId);
		this.startDate = startdate;
		this.endDate = enddate;
	}

	public ProgramCourseDTO() {
		super();
	}

	public ProgramCourseId getprogramCourseId() {
		return programCourseId;
	}

	public void setProgramId(ProgramCourseId program) {
		this.programCourseId = program;
	}

	public Date getStartdate() {
		return startDate;
	}

	public void setStartdate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEnddate() {
		return endDate;
	}

	public void setEnddate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ProgramCourseDTO [ProgramCourseId=" + programCourseId + ", startdate=" + startDate + ", enddate=" + endDate + "]";
	}

}

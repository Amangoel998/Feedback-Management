package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="batch_courses")
public class BatchCourseDTO {
	
	@Id
	@Column(name="batch")
	private String batch;
	
	@Column(name="course_id")
	private String courseId;
	

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public BatchCourseDTO(String batch, String courseId) {
		super();
		this.courseId = courseId;
		this.batch = batch;
	}

	public BatchCourseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "BatchCourseDTO [courseId=" + courseId + ", batch=" + batch + "]";
	}
	
	
}

package com.cg.feedback.dto;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name="courses")
public class CourseDTO {
	
	@Id
	@Column(name="course_id", updatable=false)
	private String courseId;
	
	@Column(name="course_name", updatable=false)
	private String courseName;
	//Constructor
	public CourseDTO(String courseId, String courseName){
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	//Getter and Setters

	public CourseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "CourseDTO [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}

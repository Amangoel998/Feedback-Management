package com.cg.feedback.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProgramCourseId implements Serializable{
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String programId;

    public ProgramCourseId() {
    }

    public ProgramCourseId(String courseId, String programId) {
        this.courseId = courseId;
        this.programId = programId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "ProgramCourseId [courseId=" + courseId + ", programId=" + programId + "]";
    }
    
}
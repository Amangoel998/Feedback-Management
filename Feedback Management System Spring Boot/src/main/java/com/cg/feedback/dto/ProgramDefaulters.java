package com.cg.feedback.dto;

import java.sql.Date;

public interface ProgramDefaulters {
    String getBatch();
    String getStudentName();
    String getStudentId();
    String getStudentEmail();
    Date getStartDate();
    Date getEndDate();
}
package com.cg.feedback.dto;

import java.sql.Date;

public interface TrainerDefaulters {
    String getBatch();
    String getProgramId();
    String getStudentName();
    String getStudentId();
    String getStudentEmail();
    Date getStartDate();
    Date getEndDate();
}
package com.cg.feedback.dto;

public class TrainerSkillDTO {
    private String trainerId;
    private String skills;

    public TrainerSkillDTO() {
    }

    public TrainerSkillDTO(String trainerId, String skills) {
        this.trainerId = trainerId;
        this.skills = skills;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
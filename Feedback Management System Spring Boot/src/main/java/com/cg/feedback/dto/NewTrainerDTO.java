package com.cg.feedback.dto;

public class NewTrainerDTO {
    private String trainerId;
    private String trainerName;
    private String trainerPass;
    private String trainerEmail;
    private String trainerSkills;

    public NewTrainerDTO() {
    }

    public NewTrainerDTO(String trainerId, String trainerName, String trainerPass, String trainerEmail,
            String trainerSkills) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.trainerPass = trainerPass;
        this.trainerEmail = trainerEmail;
        this.trainerSkills = trainerSkills;
    }

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

    public String getTrainerPass() {
        return trainerPass;
    }

    public void setTrainerPass(String trainerPass) {
        this.trainerPass = trainerPass;
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
}
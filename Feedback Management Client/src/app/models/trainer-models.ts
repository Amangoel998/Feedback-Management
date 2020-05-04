export interface NewTrainer {
    trainerId: String;
    trainerName: String;
    trainerEmail: String;
    trainerPass: String;
    trainerSkills: String;
}
export interface Trainer {
    trainerId: String;
    trainerName: String;
    trainerEmail: String;
    trainerSkills: String;
}
export interface NewTrainerSkill {
    trainerId: String;
    skills: String;
}
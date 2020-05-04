export interface TrainerProgramId{
    trainerId: String;
    programId:String;
    batch: String;
}
export interface TrainerProgram{
    trainerProgramId: TrainerProgramId;
}
export interface ProgramCourseId{
    courseId:String;
    programId: String;
}
export interface ProgramCourse{
    programCourseId: ProgramCourseId;
    startdate: Date;
    enddate: Date;
}
export interface NewStudent {
  studentId: String;
  studentName: String;
  studentEmail: String;
  studentPass: String;
  batch: String;
}
export interface Student {
  studentId: String;
  studentName: String;
  studentEmail: String;
  batch: String;
}
export interface Feedback {
    studentId: String
    trainerId: String;
	  programId: String;
    q1: Number;
    q2: Number;
    q3: Number;
    q4: Number;
    q5: Number;
    comments: String;
    suggestions: String;
}
export interface AvailableProgram{
    programId: String;
    programName: String;
    trainerId: String;
    TrainerName: String;
    startdate: Date;
    enddate: Date;
}
export enum QuestionSet{
  q1 = 'Presentation and communication skills of faculty',
  q2 = 'Ability to clarify doubts and explain difficult points',
  q3 = 'Time management in completing the contents',
  q4 = 'Handout provided(Student Guide)',
  q5 = 'Hardware, software and network availability',
}
export enum Rating{
  'Excellent' = 5,
  'Good' = 4,
  'Average' = 3,
  'Below Average' = 2,
  'Poor' = 1
}
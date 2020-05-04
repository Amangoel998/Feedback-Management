export interface Report {
    q1: Number;
    q2: Number;
    q3: Number;
    q4: Number;
    q5: Number;
    comments: Array<String>;
    suggestions: Array<String>;
  }
  export interface ProgramDefaulter {
    batch: String;
    studentName: String;
    studentId: String;
    studentEmail: String;
    startDate: Date;
    endDate: Date;
  }
  export interface TrainerDefaulter {
    batch: String;
    studentName: String;
    studentId: String;
    studentEmail: String;
    programId: String;
    startDate: Date;
    endDate: Date;
  }
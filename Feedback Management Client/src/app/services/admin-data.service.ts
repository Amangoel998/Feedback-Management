import { Injectable, OnInit } from '@angular/core';
import { ServerService } from './server.service';
import { Observable } from 'rxjs';
import { Batch, Course, Program } from '../models/admin-models';
import { Trainer, NewTrainer, NewTrainerSkill } from '../models/trainer-models';
import { Student, NewStudent } from '../models/student-models';
import { ProgramCourse, TrainerProgram, ProgramCourseId, TrainerProgramId } from '../models/table-models';

@Injectable({
  providedIn: 'root'
})
export class AdminDataService implements OnInit{

  constructor(private server:ServerService) { }
  ngOnInit(): void {
    
  }
  // ----------GET Requests------------
  getBatches(): Observable<any>{
    return this.server.request('GET','admin/batches');
  }
  getCourses(): Observable<any>{
    return this.server.request('GET','admin/courses');
  }
  getPrograms(): Observable<any>{
    return this.server.request('GET','admin/programs');
  }
  getTrainers(): Observable<any>{
    return this.server.request('GET','admin/trainers');
  }
  getStudents(): Observable<any>{
    return this.server.request('GET','admin/students');
  }
  getProgramTrainers(): Observable<any>{
    return this.server.request('GET','admin/programTrainers');
  }
  getCoursePrograms(): Observable<any>{
    return this.server.request('GET','admin/coursePrograms');
  }
  getreportByProgram(programId): Observable<any>{
    return this.server.request('GET','admin/programReport', {programId:programId})
  }
  getreportByTrainer(trainerId): Observable<any>{
    return this.server.request('GET','admin/trainerReport', {trainerId:trainerId})
  }
  getdefaultersByProgram(programId): Observable<any>{
    if(programId)
      return this.server.request('GET','admin/programFeedbackDefaulters', {programId:programId})
  }
  getdefaultersByTrainer(trainerId): Observable<any>{
    if(trainerId)
      return this.server.request('GET','admin/trainerFeedbackDefaulters', {trainerId:trainerId})
  }

  // ----------POST Requests------------
  addBatch(batch:Batch): Observable<any>{
    return this.server.request('POST','admin/addBatch', batch);
  }
  addCourse(course:Course): Observable<any>{
    return this.server.request('POST','admin/addCourse',course);
  }
  addProgram(program: Program): Observable<any>{
    return this.server.request('POST','admin/addProgram',program);
  }
  addTrainer(trainer:NewTrainer): Observable<any>{
    return this.server.request('POST','admin/addTrainer', trainer);
  }
  addStudent(student: NewStudent): Observable<any>{
    return this.server.request('POST','admin/addStudent', student);
  }
  addProgramToCourse(courseprogram: ProgramCourse): Observable<any>{
    return this.server.request('POST','admin/addProgramToCourse', courseprogram);
  }
  addTrainerToProgram(programtrainer: TrainerProgram): Observable<any>{
    return this.server.request('POST','admin/addTrainerToProgram', programtrainer);
  }

  // ----------PUT Requests------------
  updateTrainerSkill(trainerskill: NewTrainerSkill): Observable<any>{
    return this.server.request('PUT','admin/maintainTrainerSkills', trainerskill);
  }

  // ----------DELETE Requests------------
  removeCourse(courseId): Observable<any>{
    return this.server.request('DELETE','admin/removeCourse',courseId);
  }
  removeProgram(programId): Observable<any>{
    return this.server.request('DELETE','admin/removeProgram',programId);
  }
  removeTrainer(trainerId): Observable<any>{
    return this.server.request('DELETE','admin/removeTrainer', trainerId);
  }
  removeStudent(studentId): Observable<any>{
    return this.server.request('DELETE','admin/removeStudent', studentId);
  }
  removeBatch(batch): Observable<any>{
    return this.server.request('DELETE','admin/removeBatch', batch);
  }
  removeProgramFromCourse(courseprogram: ProgramCourseId): Observable<any>{
    const programcourseId = courseprogram.courseId+'-'+courseprogram.programId;
    return this.server.request('DELETE','admin/removeProgramFromCourse', {programcourseId:programcourseId});
  }
  removeTrainerFromProgram(programtrainer: TrainerProgramId): Observable<any>{
    const trainerprogramId = programtrainer.trainerId+'-'+programtrainer.programId+'-'+programtrainer.batch;
    return this.server.request('DELETE','admin/removeTrainerFromProgram', {trainerprogramId:trainerprogramId});
  }
}

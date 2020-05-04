import { Injectable } from '@angular/core';
import { Feedback } from '../models/student-models';
import { Observable } from 'rxjs';
import { ServerService } from './server.service';
import { NotifierService } from 'angular-notifier';

@Injectable({
  providedIn: 'root'
})
export class StudentDataService {

  constructor(private server:ServerService, private notificationSrv:NotifierService) { }
  getUserId(){
    if(this.server.user() && this.server.user().role==='student')
      return this.server.user().userid
    this.notificationSrv.notify('error',"User Error: Can't Proceed with the request");
  }
  getPrograms(): Observable<any>{
    const studentId = this.getUserId();
    if(studentId)
      return this.server.request('GET','student/programs', {studentId:studentId});
  }
  addFeedback(feedback): Observable<any>{
    const studentId = this.getUserId()
    if(studentId){
      Object.assign(feedback,{studentId:studentId})
      return this.server.request('POST','student/giveFeedback',feedback);
    }
  }
}

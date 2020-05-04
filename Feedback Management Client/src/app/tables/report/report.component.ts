import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Report } from 'src/app/models/report-defaulters-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { QuestionSet } from 'src/app/models/student-models';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnChanges {
  public report: Report
  @Input() programId: String = '';
  @Input() trainerId: String = '';
  public questions = QuestionSet;
  constructor(private adminSrv:AdminDataService) {}
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.initialize();
  }
  initialize(){
    if(this.programId)
      this.adminSrv.getreportByProgram(this.programId).subscribe(e=>{
        e.q1 = e.q1.toPrecision(3)
        e.q2 = e.q2.toPrecision(3)
        e.q3 = e.q3.toPrecision(3)
        e.q4 = e.q4.toPrecision(3)
        e.q5 = e.q5.toPrecision(3)
        this.report = e
    })
    else if(this.trainerId)
    this.adminSrv.getreportByTrainer(this.trainerId).subscribe(e=>{
        e.q1 = e.q1.toPrecision(3)
        e.q2 = e.q2.toPrecision(3)
        e.q3 = e.q3.toPrecision(3)
        e.q4 = e.q4.toPrecision(3)
        e.q5 = e.q5.toPrecision(3)
        this.report = e
    })
  }

}

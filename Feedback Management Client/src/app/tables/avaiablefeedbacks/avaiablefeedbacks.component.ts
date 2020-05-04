import { Component, OnInit } from '@angular/core';
import { AvailableProgram } from 'src/app/models/student-models';
import { StudentDataService } from 'src/app/services/student-data.service';
import { MatDialog } from '@angular/material/dialog';
import { FeedbackFormComponent } from 'src/app/dashboards/student-dashboard/feedback-form/feedback-form.component';

@Component({
  selector: 'app-availablefeedbacks',
  templateUrl: './avaiablefeedbacks.component.html',
  styleUrls: ['./avaiablefeedbacks.component.css']
})
export class AvailablefeedbacksComponent implements OnInit {
  available_feedbacks:AvailableProgram[] 
  displayedColumns: string[] = ['programId', 'programName', 'trainerId', 'trainerName', 'startdate', 'enddate', 'feedbacks'];
  constructor(private studentSrv:StudentDataService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.initialize()
  }
  initialize(){
    this.studentSrv.getPrograms().subscribe(e=>{
      e.map(el=>{
        el.startDate = new Date(el.startDate).toDateString() 
        el.endDate = new Date(el.endDate).toDateString()
      })
      this.available_feedbacks = e;
    })
  }
  
  addFeedback(data){
    let dialogRef = this.dialog.open(FeedbackFormComponent, {
      width: '600px',
      height: '600px',
      data : data
    });
    dialogRef.afterClosed().subscribe(result => {
      this.initialize();
    }); 
  }

}

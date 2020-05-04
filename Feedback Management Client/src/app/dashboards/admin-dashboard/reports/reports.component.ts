import { Component, OnInit } from '@angular/core';
import { Trainer } from 'src/app/models/trainer-models';
import { Program } from 'src/app/models/admin-models';
import { AdminDataService } from 'src/app/services/admin-data.service';

@Component({
  selector: 'app-admin-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  programId: String = '';
  trainerId: String = '';

  constructor() { }

  ngOnInit(): void {
  }
  
  programValueChange(event){
    this.programId = event;
  }
  trainerValueChange(event){
    this.trainerId = event;
  }
}

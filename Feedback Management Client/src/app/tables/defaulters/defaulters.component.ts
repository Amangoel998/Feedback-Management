import { Component, OnInit, Input, OnChanges } from '@angular/core';
import {
  ProgramDefaulter,
  TrainerDefaulter,
} from 'src/app/models/report-defaulters-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Program } from 'src/app/models/admin-models';
import { Trainer } from 'src/app/models/trainer-models';

@Component({
  selector: 'app-program-defaulters',
  templateUrl: './programdefaulters.component.html',
  styleUrls: ['./defaulters.component.css'],
})
export class ProgramDefaultersComponent implements OnChanges {
  @Input() programId: String = '';
  public defaulters_list: ProgramDefaulter[];
  displayedColumns: string[] = [
    'index',
    'batch',
    'studentId',
    'studentName',
    'studentEmail',
    'startdate',
    'enddate',
  ];
  constructor(private adminSrv: AdminDataService) {}
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.initialize();
  }
  private initialize() {
    this.adminSrv.getdefaultersByProgram(this.programId).subscribe((e) => {
      e.map((el) => {
        el.startDate = new Date(el.startDate).toDateString();
        el.endDate = new Date(el.endDate).toDateString();
      });
      this.defaulters_list = e;
    });
  }
}

@Component({
  selector: 'app-trainer-defaulters',
  templateUrl: './trainerdefaulters.component.html',
  styleUrls: ['./defaulters.component.css'],
})
export class TrainerDefaultersComponent implements OnChanges {
  @Input() trainerId: String = '';
  public defaulters_list: TrainerDefaulter[];
  displayedColumns: string[] = [
    'index',
    'batch',
    'program',
    'studentId',
    'studentName',
    'studentEmail',
    'startdate',
    'enddate',
  ];
  constructor(private adminSrv: AdminDataService) {}
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.initialize();
  }
  private initialize() {
    this.adminSrv.getdefaultersByTrainer(this.trainerId).subscribe((e) => {
      e.map((el) => {
        el.startDate = new Date(el.startDate).toDateString();
        el.endDate = new Date(el.endDate).toDateString();
      });
      this.defaulters_list = e;
    });
  }
}

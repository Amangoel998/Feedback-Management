import { Component, OnInit } from '@angular/core';
import { Trainer } from 'src/app/models/trainer-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Program } from 'src/app/models/admin-models';

@Component({
  selector: 'app-admin-defaulters',
  templateUrl: './defaulters.component.html',
  styleUrls: ['./defaulters.component.css']
})
export class DefaultersComponent implements OnInit {
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

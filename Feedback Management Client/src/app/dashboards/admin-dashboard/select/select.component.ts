import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Program } from 'src/app/models/admin-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Trainer } from 'src/app/models/trainer-models';

@Component({
  selector: 'app-select-programs',
  templateUrl: './select-programs.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectProgramsComponent implements OnInit {
  public program_list: Program[]
  constructor(private adminSrv:AdminDataService) { }
  @Output() programId: EventEmitter<any> = new EventEmitter();
  ngOnInit(): void {
    this.initialize()
  }
  initialize(){
    this.adminSrv.getPrograms().subscribe(e=>this.program_list = e)
  }
  selectProgram(event){
    this.programId.emit(event.value);
  }

}

@Component({
  selector: 'app-select-trainers',
  templateUrl: './select-trainer.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectTrainersComponent implements OnInit {
  public trainer_list: Trainer[]
  @Output() trainerId: EventEmitter<any> = new EventEmitter();
  constructor(private adminSrv:AdminDataService) { }

  ngOnInit(): void {
    this.initialize();
  }
  initialize(){
    this.adminSrv.getTrainers().subscribe(e=>this.trainer_list = e)
  }
  selectTrainer(event){
    this.trainerId.emit(event.value);
  }

}

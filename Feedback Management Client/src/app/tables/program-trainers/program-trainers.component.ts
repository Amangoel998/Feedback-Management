import { Component, OnInit } from '@angular/core';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Program, Batch } from 'src/app/models/admin-models';
import { TrainerProgram, TrainerProgramId } from 'src/app/models/table-models';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Trainer } from 'src/app/models/trainer-models';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-program-trainers',
  templateUrl: './program-trainers.component.html',
  styleUrls: ['./program-trainers.component.css']
})
export class ProgramTrainersComponent implements OnInit {
  public programtrainer_list: TrainerProgramId[]
  public deleteprogramtrainer = new FormControl('')
  displayedColumns: string[] = ['index', 'trainer','program','batch'];
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
    this.adminSrv.getProgramTrainers().subscribe(e=>{
      this.programtrainer_list = e.map(el=>el.trainerProgramId)
    })
    
  }
  public removeProgramTrainer(){
    if(this.deleteprogramtrainer.value){
      this.adminSrv.removeTrainerFromProgram(this.deleteprogramtrainer.value).subscribe(e=>e)
      this.initialize()
    }
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddProgramTrainersComponent, {
      width: '350px',
      height: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.initialize();
    }); 
  }
}

@Component({
  selector: 'add-program-trainers',
  templateUrl: './addtrainerprogram.component.html',
  styleUrls: ['./program-trainers.component.css']
})
export class AddProgramTrainersComponent implements OnInit {
  trainers: Trainer[];
  programs: Program[];
  batches: Batch[];
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<ProgramTrainersComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  get trainerId(){
    return this.form.controls['trainerId'];
  }
  get programId(){
    return this.form.controls['programId'];
  }
  get batch(){
    return this.form.controls['batch'];
  }
  private initialize(){
    this.adminSrv.getTrainers().subscribe(
      e=> this.trainers = e
    )
    this.adminSrv.getPrograms().subscribe(
      e=> this.programs = e
    )
    this.adminSrv.getBatches().subscribe(
      e=> this.batches = e
    )
    this.form = new FormGroup({
      trainerId: new FormControl('', [ Validators.required ]),
      programId: new FormControl('', [ Validators.required ]),
      batch: new FormControl('', [ Validators.required ]),
    });
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error',"Invalid Form Data: Add Program Trainer Form Data is Invalid");
    }
    const payload: TrainerProgram = {
      trainerProgramId: {
        trainerId: this.form.value.trainerId,
        programId: this.form.value.programId,
        batch: this.form.value.batch
      }
    }
    this.adminSrv.addTrainerToProgram(payload).subscribe(e=>this.dialogRef.close());
  }
}

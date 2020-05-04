import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Trainer } from 'src/app/models/trainer-models';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {
  public trainers_list: Trainer[]
  public rmvTrainer = new FormControl('', [Validators.required])
  displayedColumns: string[] = ['index', 'id', 'name', 'email', 'skills'];
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
      this.adminSrv.getTrainers().subscribe(e=>{
      this.trainers_list = e
    })
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddTrainersComponent, {
      width: '300px',
      height: '460px',
    });
    dialogRef.afterClosed().subscribe(
      result => this.initialize()
    )
  }
  removeTrainer(){
    if(this.rmvTrainer.value){
      this.adminSrv.removeTrainer({trainerId:this.rmvTrainer.value}).subscribe(e=>e)
      this.initialize();
    }
  }

}

@Component({
  selector: 'add-trainers',
  templateUrl: './addtrainer.component.html',
  styleUrls: ['./trainers.component.css']
})
export class AddTrainersComponent implements OnInit {
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<TrainersComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
    this.form = new FormGroup({
      trainerId: new FormControl('', [ Validators.required, Validators.pattern('(TRN){1}[0-9]{3}$') ]),
      trainerName: new FormControl('', [ Validators.required ]),
      trainerEmail:new FormControl('', [ Validators.required, Validators.email ]),
      trainerPass: new FormControl('', [ Validators.required, Validators.minLength(6)]),
      trainerSkills:new FormControl('', [ Validators.required ]),
    });
  }
  get trainerId(){
    return this.form.controls['trainerId'];
  }
  get trainerName(){
    return this.form.controls['trainerName'];
  }
  get trainerPass(){
    return this.form.controls['trainerPass'];
  }
  get trainerEmail(){
    return this.form.controls['trainerEmail'];
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error',"Invalid Form Data: Add Trainer Form Data is Invalid");
    }
    this.adminSrv.addTrainer(this.form.value).subscribe(e=>this.dialogRef.close());
  }
}
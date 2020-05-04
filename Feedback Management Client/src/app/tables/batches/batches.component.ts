import { Component, OnInit } from '@angular/core';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Batch, Course } from 'src/app/models/admin-models';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-batches',
  templateUrl: './batches.component.html',
  styleUrls: ['./batches.component.css']
})
export class BatchesComponent implements OnInit {
  public batches_list: Batch[]
  public removeForm: FormGroup
  public rmvBatch = new FormControl('', [Validators.required])
  displayedColumns: string[] = ['index', 'batch', 'course'];
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
      this.adminSrv.getBatches().subscribe(e=>{
      this.batches_list = e
    })
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddBatchComponent, {
      width: '300px',
      height: '275px',
    });
    dialogRef.afterClosed().subscribe(
      result => this.initialize()
    )
  }
  removeBatch(){
    if(this.rmvBatch.value){
      this.adminSrv.removeBatch({batch:this.rmvBatch.value}).subscribe(e=>e)
      this.initialize();
    }
  }
  
}
@Component({
  selector: 'app-add-batch',
  templateUrl: './addbatch.component.html',
  styleUrls: ['./batches.component.css']
})
export class AddBatchComponent implements OnInit {
  courses: Course[];
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<BatchesComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  get batch(){
    return this.form.controls['batch'];
  }
  private initialize(){
    this.adminSrv.getCourses().subscribe(
      e=> this.courses = e
    )
    this.form = new FormGroup({
      batch: new FormControl('', [Validators.required]),
      courseId: new FormControl('', [ Validators.required ]),
    });
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error',"Invalid Form Data: Add Batch Form Data is Invalid");
    }
    this.adminSrv.addBatch(this.form.value).subscribe(e=>this.dialogRef.close());
  }
}
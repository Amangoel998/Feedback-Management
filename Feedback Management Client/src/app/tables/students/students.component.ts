import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Validators, FormControl, FormGroup } from '@angular/forms';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  public students_list: Student[]
  public rmvStudent = new FormControl('', [Validators.required])
  displayedColumns: string[] = ['index', 'batch', 'id', 'name', 'email'];
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
      this.adminSrv.getStudents().subscribe(e=>{
      this.students_list = e
    })
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddStudentsComponent, {
      width: '300px',
      height: '450px',
    });
    dialogRef.afterClosed().subscribe(
      result => this.initialize()
    )
  }
  removeStudent(){
    if(this.rmvStudent.value){
      this.adminSrv.removeStudent({studentId:this.rmvStudent.value}).subscribe(e=>e)
      this.initialize();
    }
  }

}

@Component({
  selector: 'add-student',
  templateUrl: './addstudent.component.html',
  styleUrls: ['./students.component.css']
})
export class AddStudentsComponent implements OnInit {
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<StudentsComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
    this.form = new FormGroup({
      studentId: new FormControl('', [ Validators.required, Validators.pattern('(STD){1}[0-9]{3}$') ]),
      studentName: new FormControl('', [ Validators.required ]),
      studentEmail:new FormControl('', [ Validators.required, Validators.email ]),
      studentPass: new FormControl('', [ Validators.required, Validators.minLength(6)]),
      batch:new FormControl('', [ Validators.required ]),
    });
  }
  get studentId(){
    return this.form.controls['studentId'];
  }
  get studentName(){
    return this.form.controls['studentName'];
  }
  get studentPass(){
    return this.form.controls['studentPass'];
  }
  get studentEmail(){
    return this.form.controls['studentEmail'];
  }
  get batch(){
    return this.form.controls['batch'];
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error',"Invalid Form Data: Add Student Form Data is Invalid");
    }
    this.adminSrv.addStudent(this.form.value).subscribe(e=>this.dialogRef.close());
  }
}
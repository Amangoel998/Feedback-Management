import { Component, OnInit } from '@angular/core';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { ProgramCourse } from 'src/app/models/table-models';
import { MatTableDataSource } from '@angular/material/table';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Program, Course } from 'src/app/models/admin-models';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-course-programs',
  templateUrl: './course-programs.component.html',
  styleUrls: ['./course-programs.component.css']
})
export class CourseProgramsComponent implements OnInit {
  public programcourse_list: ProgramCourse[]
  public deletecourseprogram = new FormControl('')
  displayedColumns: string[] = ['index', 'course','program','startdate', 'enddate'];
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
    this.adminSrv.getCoursePrograms().subscribe(e=>{
      e.map(el=>{
        el.startdate = new Date(el.startdate).toDateString() 
        el.enddate = new Date(el.enddate).toDateString()
      })
      this.programcourse_list = e
    })
    
  }
  public removeCourseProgram(){
    if(this.deletecourseprogram.value){
      this.adminSrv.removeProgramFromCourse(this.deletecourseprogram.value).subscribe(e=>e)
      this.initialize()
    }
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddCourseProgramComponent, {
      width: '350px',
      height: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.initialize();
    }); 
  }
}

@Component({
  selector: 'add-course-program',
  templateUrl: './addcourseprogram.component.html',
  styleUrls: ['./course-programs.component.css']
})
export class AddCourseProgramComponent implements OnInit {
  courses: Course[];
  programs: Program[]
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<CourseProgramsComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  get courseId(){
    return this.form.controls['courseId'];
  }
  get programId(){
    return this.form.controls['programId'];
  }
  get startdate(){
    return this.form.controls['startdate'];
  }
  get enddate(){
    return this.form.controls['enddate'];
  }
  private initialize(){
    this.adminSrv.getCourses().subscribe(
      e=> this.courses = e
    )
    this.adminSrv.getPrograms().subscribe(
      e=> this.programs = e
    )
    this.form = new FormGroup({
      courseId: new FormControl('', [ Validators.required ]),
      programId: new FormControl('', [ Validators.required ]),
      startdate: new FormControl('', [ Validators.required ]),
      enddate: new FormControl('', [ Validators.required ]),
    });
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error',"Invalid Form Data: Add Course Program Form Data is Invalid");
    }
    const payload: ProgramCourse = {
      programCourseId: {
        courseId: this.form.value.courseId,
        programId: this.form.value.programId
      },
      startdate: this.form.value.startdate.toISOString().slice(0, 10),
      enddate: this.form.value.enddate.toISOString().slice(0, 10),
    }
    this.adminSrv.addProgramToCourse(payload).subscribe(e=>this.dialogRef.close());
  }
}

import { Component, OnInit } from '@angular/core';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { Course } from 'src/app/models/admin-models';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  public courses_list: Course[]
  displayedColumns: string[] = ['index', 'id', 'name'];
  public deletecourse = new FormControl('')
  constructor(private adminSrv:AdminDataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initialize();
  }
  private initialize(){
      this.adminSrv.getCourses().subscribe(e=>{
      this.courses_list = e
    })
  }
  addDialog(){
    let dialogRef = this.dialog.open(AddCourseComponent, {
      width: '300px',
      height: '275px',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.initialize();
    }); 
  }
  removeCourse(){
    if(this.deletecourse.value){
      this.adminSrv.removeCourse({courseId:this.deletecourse.value}).subscribe(e=>e)
      this.initialize();
    }
  }
}

@Component({
  selector: 'app-add-course',
  templateUrl: './addcourse.component.html',
  styleUrls: ['./courses.component.css']
})
export class AddCourseComponent implements OnInit {
  courses: Course[];
  form : FormGroup
  constructor(private adminSrv:AdminDataService, private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<CoursesComponent>) {}
  ngOnInit(): void {
    this.initialize();
  }
  get courseId(){
    return this.form.controls['courseId'];
  }
  get courseName(){
    return this.form.controls['courseName'];
  }
  private initialize(){
    this.adminSrv.getCourses().subscribe(
      e=> this.courses = e
    )
    this.form = new FormGroup({
      courseId: new FormControl('', [ Validators.required, Validators.pattern('(CRS){1}[0-9]{3}$') ]),
      courseName: new FormControl('', [ Validators.required ]),
    });
  }
  public onSubmit(){
    if(!this.form.valid){
      return this.notifySrv.notify('error','Invalid Form Data: Add Course Form Data is Invalid');
    }
    this.adminSrv.addCourse(this.form.value).subscribe(e=>this.dialogRef.close());
  }
}

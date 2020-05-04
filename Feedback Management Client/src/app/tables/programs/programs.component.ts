import { Component, OnInit } from '@angular/core';
import { Program, Course } from 'src/app/models/admin-models';
import { AdminDataService } from 'src/app/services/admin-data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.css'],
})
export class ProgramsComponent implements OnInit {
  public programs_list: Program[];
  displayedColumns: string[] = ['index', 'id', 'name'];
  public deleteprogram = new FormControl('');
  constructor(private adminSrv: AdminDataService, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.initialize();
  }
  private initialize() {
    this.adminSrv.getPrograms().subscribe((e) => {
      this.programs_list = e;
    });
  }
  addDialog() {
    let dialogRef = this.dialog.open(AddProgramComponent, {
      width: '300px',
      height: '275px',
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.initialize();
    });
  }
  removeProgram() {
    if (this.deleteprogram.value) {
      return this.adminSrv
        .removeProgram({ programId: this.deleteprogram.value })
        .subscribe((e) => {
          return this.initialize();
        });
    }
  }
}

@Component({
  selector: 'app-add-program',
  templateUrl: './addprogram.component.html',
  styleUrls: ['./programs.component.css'],
})
export class AddProgramComponent implements OnInit {
  programs: Program[];
  form: FormGroup;
  constructor(
    private adminSrv: AdminDataService,
    private notifySrv: NotifierService,
    private dialogRef: MatDialogRef<ProgramsComponent>
  ) {}
  ngOnInit(): void {
    this.initialize();
  }
  get programId() {
    return this.form.controls['programId'];
  }
  get programName() {
    return this.form.controls['programName'];
  }
  private initialize() {
    this.adminSrv.getCourses().subscribe((e) => (this.programs = e));
    this.form = new FormGroup({
      programId: new FormControl('', [ Validators.required, Validators.pattern('(PRG){1}[0-9]{3}$'), ]),
      programName: new FormControl('', [Validators.required]),
    });
  }
  public onSubmit() {
    if (!this.form.valid) {
      return this.notifySrv.notify('error',
        'Invalid Form Data: Add Program Form Data is Invalid'
      );
    }
    this.adminSrv
      .addProgram(this.form.value)
      .subscribe((e) => this.dialogRef.close());
  }
}

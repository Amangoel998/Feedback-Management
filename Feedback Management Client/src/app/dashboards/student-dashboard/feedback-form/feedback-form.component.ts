import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {
  AvailableProgram,
  Feedback,
  QuestionSet,
  Rating,
} from 'src/app/models/student-models';
import { StudentDataService } from 'src/app/services/student-data.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css'],
})
export class FeedbackFormComponent implements OnInit {
  constructor(
    private studentSrv: StudentDataService,
    public dialogRef: MatDialogRef<AvailableProgram>,
    private notifySrv: NotifierService,
    @Inject(MAT_DIALOG_DATA) public data
  ) {}

  feedback_data: Feedback;
  form: FormGroup;
  questions = QuestionSet;
  rating: Rating;
  ratings = Object.keys(Rating).slice(5);
  ngOnInit(): void {
    this.form = new FormGroup({
      q1: new FormControl('', [Validators.required]),
      q2: new FormControl('', [Validators.required]),
      q3: new FormControl('', [Validators.required]),
      q4: new FormControl('', [Validators.required]),
      q5: new FormControl('', [Validators.required]),
      comments: new FormControl('', [Validators.required]),
      suggestions: new FormControl('', [Validators.required]),
    });
  }
  sendFeedback() {
    if (!this.form.valid)
      return this.notifySrv.notify('error','Invalid Form Data: Invalid Feedback Form Data'
      );

    const payload = {
      programId: this.data.programId,
      trainerId: this.data.trainerId,
      q1: Number(Rating[this.form.value.q1]),
      q2: Number(Rating[this.form.value.q2]),
      q3: Number(Rating[this.form.value.q3]),
      q4: Number(Rating[this.form.value.q4]),
      q5: Number(Rating[this.form.value.q5]),
    };
    Object.assign(this.form.value, payload);
    return this.studentSrv
      .addFeedback(this.form.value)
      .subscribe((e) => this.dialogRef.close());
  }
}

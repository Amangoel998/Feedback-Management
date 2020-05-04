import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FeedbackFormComponent } from './dashboards/student-dashboard/feedback-form/feedback-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NotifierModule } from 'angular-notifier';
import {customNotifierOptions} from './models/notifier-conf';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from './material-module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { LoginComponent } from './login/login.component';
import { StudentDashboardComponent } from './dashboards/student-dashboard/student-dashboard.component';
import { TrainerDashboardComponent } from './dashboards/trainer-dashboard/trainer-dashboard.component';
import { AdminDashboardComponent } from './dashboards/admin-dashboard/admin-dashboard.component';

// import { HttpErrorInterceptor } from './services/http-error.interceptor';
import { StudentDataService } from './services/student-data.service';
import { TrainerDataService } from './services/trainer-data.service';
import { AdminDataService } from './services/admin-data.service';
import { ServerService } from './services/server.service';
import { ErrorsHandler } from './services/error-handler';
import { BatchesComponent, AddBatchComponent } from './tables/batches/batches.component';
import { CourseProgramsComponent, AddCourseProgramComponent } from './tables/course-programs/course-programs.component';
import { ProgramTrainersComponent, AddProgramTrainersComponent } from './tables/program-trainers/program-trainers.component';
import { CoursesComponent, AddCourseComponent } from './tables/courses/courses.component';
import { ProgramsComponent, AddProgramComponent } from './tables/programs/programs.component';
import { ProgramDefaultersComponent, TrainerDefaultersComponent } from './tables/defaulters/defaulters.component';
import { StudentsComponent, AddStudentsComponent } from './tables/students/students.component';
import { TrainersComponent, AddTrainersComponent } from './tables/trainers/trainers.component';
import { AvailablefeedbacksComponent } from './tables/avaiablefeedbacks/avaiablefeedbacks.component';

import { ReportComponent } from './tables/report/report.component';
import { ReportsComponent } from './dashboards/admin-dashboard/reports/reports.component';
import { DefaultersComponent } from './dashboards/admin-dashboard/defaulters/defaulters.component';
import { SelectProgramsComponent, SelectTrainersComponent } from './dashboards/admin-dashboard/select/select.component';
import { HttpErrorInterceptor } from './services/http-error.interceptor';

// import { ErrorsHandler } from './services/error-handler';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentDashboardComponent,
    TrainerDashboardComponent,
    AdminDashboardComponent,
    FeedbackFormComponent,
    BatchesComponent,
    AddBatchComponent,
    ProgramsComponent,
    AddProgramComponent,
    CoursesComponent,
    AddCourseComponent,
    CourseProgramsComponent,
    ProgramTrainersComponent,
    AddProgramTrainersComponent,
    ProgramDefaultersComponent,
    TrainerDefaultersComponent,
    AddCourseProgramComponent,
    StudentsComponent,
    AddStudentsComponent,
    TrainersComponent,
    AddTrainersComponent,
    AvailablefeedbacksComponent,
    ReportComponent,
    ReportsComponent,
    DefaultersComponent,
    SelectTrainersComponent,
    SelectProgramsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  providers: [
    ServerService,
    StudentDataService,
    TrainerDataService,
    AdminDataService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true,
    },
    // {
    //   provide: ErrorHandler,
    //   useClass: ErrorsHandler
    // }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

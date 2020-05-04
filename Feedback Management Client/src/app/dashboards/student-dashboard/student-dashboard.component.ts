import { Component, OnInit } from '@angular/core';
import { ServerService } from 'src/app/services/server.service';
import { LoginResponse } from 'src/app/models/login-models';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {
  constructor(private server: ServerService) { }
  user: LoginResponse
  ngOnInit(): void {
    this.user = this.server.user()
  }
  logout(){
    this.server.logout();
  }
}

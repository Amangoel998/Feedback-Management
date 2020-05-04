import { Component, OnInit } from '@angular/core';
import { ServerService } from '../../services/server.service';
import { LoginResponse } from 'src/app/models/login-models';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  constructor(private server: ServerService) { }
  user: LoginResponse
  ngOnInit(): void {
    this.user = this.server.user()
  }
  logout(){
    this.server.logout();
  }
}

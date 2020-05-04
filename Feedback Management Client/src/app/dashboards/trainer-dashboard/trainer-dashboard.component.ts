import { Component, OnInit } from '@angular/core';
import { ServerService } from '../../services/server.service';
import { Router } from '@angular/router';
import { TrainerDataService } from 'src/app/services/trainer-data.service';
import { LoginResponse } from 'src/app/models/login-models';

@Component({
  selector: 'app-trainer-dashboard',
  templateUrl: './trainer-dashboard.component.html',
  styleUrls: ['./trainer-dashboard.component.css']
})
export class TrainerDashboardComponent implements OnInit {
  trainerId: String = ''
  constructor(private server: ServerService,private trainerSrv: TrainerDataService, private router:Router) {}
  user: LoginResponse
  
  ngOnInit(): void {
    this.initialize()
  }
  initialize(){
    this.user = this.server.user()
    this.trainerId = this.trainerSrv.getUserId();
  }
  logout(){
    this.server.logout();
  }

}

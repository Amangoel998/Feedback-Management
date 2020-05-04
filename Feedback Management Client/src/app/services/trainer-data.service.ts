import { Injectable } from '@angular/core';
import { ServerService } from './server.service';
import { NotifierService } from 'angular-notifier';

@Injectable({
  providedIn: 'root'
})
export class TrainerDataService {
  constructor(private server:ServerService, private notificationSrv:NotifierService) { }
  getUserId(){
    if(!this.server.user() || this.server.user().role!='trainer')
      this.notificationSrv.notify('error',"User Error: Can't Proceed with the request");
    return this.server.user().userid
  }
}

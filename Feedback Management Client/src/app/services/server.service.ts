import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import {
  BehaviorSubject,
  throwError,
  Observable,
} from 'rxjs';
import { LoginResponse, LoginRequest } from '../models/login-models';
import { NotifierService } from 'angular-notifier';

const baseUrl = '/api/';

@Injectable({
  providedIn: 'root',
})
export class ServerService implements OnInit {
  private loggedIn = new BehaviorSubject<boolean>(false);
  isLoggedIn(){
    if(sessionStorage.getItem('user'))
      this.loggedIn.next(true);
    let result;
    this.loggedIn.subscribe(e=>result = e)
    return result;
  }
  constructor(private http: HttpClient, private router: Router,private notifier: NotifierService) {}
  ngOnInit(): void {
    this.initialize();
  }
  private initialize() {
    sessionStorage.removeItem('user')
    // const userData = sessionStorage.getItem('user');
    // if (userData) {
    //   console.log(JSON.parse(userData))
    //   this.setLoggedIn(true);
    // }
  }
  private setLoggedIn(loggedIn: boolean) {
    this.loggedIn.next(loggedIn);
  }
  public role(): String {
    const user_role = sessionStorage.getItem('user')
    if (user_role){
      return JSON.parse(user_role).role
    }
  }
  public user(): LoginResponse{
    const user_role = sessionStorage.getItem('user')
    if (user_role)
      return JSON.parse(user_role)
  }
  public getrequest(route: string, data?: any) {
    let params = new HttpParams();
    if (data !== undefined) {
      Object.getOwnPropertyNames(data).forEach((key) => {
        params = params.set(key, data[key]);
      });
    }
    return this.http.get(baseUrl + route, {
      responseType: 'json',
      params,
    });
  }
  public request(method: string, route: string, data?: any): Observable<any> {
    if (method === 'GET') return this.getrequest(route, data);
    else if (method === 'DELETE') return this.deleterequest(route, data)
    
    const payload = {
      responseType: 'json',
      observe: 'body',
    };
    Object.assign(payload, data);
    if (method === 'POST') return this.http.post(baseUrl + route, payload);
    else if (method === 'PUT') return this.http.put(baseUrl + route, payload);
    
  }
  private deleterequest(route: string, data?: any) {
    let params = new HttpParams();
    if (data !== undefined) {
      Object.getOwnPropertyNames(data).forEach((key) => {
        params = params.set(key, data[key]);
      });
    }
    return this.http.delete(baseUrl + route, {
      responseType: 'json',
      params,
    });
  }
  private setSession(authResult: LoginResponse) {
    if(authResult)
      sessionStorage.setItem('user', JSON.stringify(authResult));
    else throwError("Invalid Data")
    this.setLoggedIn(true);
  }
  public async login(user: LoginRequest) {
    return this.http
      .post<LoginResponse>(baseUrl + 'login', user)
      .subscribe((response: LoginResponse) => {
        this.setSession(response);
        if(response.role==='student')
        this.router.navigate(['/student-dashboard'])
      else if(response.role==='trainer')
        this.router.navigate(['/trainer-dashboard'])
      else if(response.role==='admin')
        this.router.navigate(['/admin-dashboard'])
      },
      err=>{
        this.notifier.notify('error', err)
      });
  }
  public async logout() {
    this.loggedIn.next(false)
    if (this.isLoggedIn) {
      this.setLoggedIn(false);
      sessionStorage.removeItem('user');
      this.router.navigate(['/login']);
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { ServerService } from '../services/server.service';
import { LoginRequest, LoginResponse } from '../models/login-models';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  constructor(
    private server: ServerService,
    private notifier: NotifierService
  ) {}
  ngOnInit(): void {
    this.initialize();
  }
  initialize() {
    this.form = new FormGroup({
      id: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }
  get id() {
    return this.form.controls['id'];
  }
  get password() {
    return this.form.controls['password'];
  }
  submit() {
    if (!this.form.valid)
      this.notifier.notify('error', 'Invalid Login Form Data')
    else {
      this.server.login(this.form.value).then(res=>res);
    }
  }
}

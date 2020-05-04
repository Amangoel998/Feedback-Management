import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { ServerService } from './services/server.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  isUserLoggedIn: Boolean = false;
  userRole: String;

  constructor(private server: ServerService, private router: Router) {
    
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log(this.server.role(), state.url)
    if(!this.server.isLoggedIn()){
      this.router.navigate(['/login']);
      return true;
    }
  }
}

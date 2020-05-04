import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse
   } from '@angular/common/http';
   import { Observable, throwError } from 'rxjs';
   import { retry, catchError } from 'rxjs/operators';
import { NotifierService } from 'angular-notifier';
import { Injector, Injectable } from '@angular/core';
   
@Injectable()
   export class HttpErrorInterceptor implements HttpInterceptor {
     constructor(private injector: Injector){}
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const notifier = this.injector.get(NotifierService);
      return next.handle(request)
        .pipe(
          retry(1),
          catchError((error: HttpErrorResponse) => {
            let errorMessage = '';
            if (error.error instanceof ErrorEvent) {
              // client-side error
              errorMessage = `Error: ${error.error.message}`;
            } else {
              // server-side error
              errorMessage = `${error.error.message}`;
            }
            notifier.notify('error', errorMessage)
            return throwError(errorMessage)
          })
        )
    }
   }
import { Injectable, ErrorHandler, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NotifierService } from 'angular-notifier';

@Injectable()
export class ErrorsHandler implements ErrorHandler {
  constructor(
    // Because the ErrorHandler is created before the providers, we’ll have to use the Injector to get them.
    private injector: Injector
  ) {}
  handleError(error: Error | HttpErrorResponse) {
    const notifier = this.injector.get(NotifierService);
    if (error instanceof HttpErrorResponse) {
      // Server or connection error happened
      if (!navigator.onLine) {
        // Handle offline error
        // return throwError('Browser Error - No Internet Connection')
        return notifier.notify('error','Browser Error: No Internet Connection');
      } else {
        // Handle Http Error (error.status === 403, 404...)
        // return throwError(`${error.status} - ${error.message}`)
        return notifier.notify('error',`${error.status} - ${error.message}`);
      }
    } else {
      // Handle Client Error (Angular Error, ReferenceError...)
    }
  }
}

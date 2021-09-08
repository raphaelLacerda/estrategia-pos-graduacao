import { LoginService } from '../service/login.service';
import { Injectable, NgModule } from '@angular/core';
import { Observable, throwError, of } from 'rxjs';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable()
export class HttpsRequestInterceptor implements HttpInterceptor {
  constructor(private loginService: LoginService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if (!this.loginService.isLogado()) {
      return next.handle(request);
    }
    return next
      .handle(
        request.clone({
          setHeaders: {
            // Authorization: this.loginService.getToken(),
            'Content-Type': 'application/json',
          },
        })
      )
      .pipe() as Observable<HttpEvent<any>>;
  }
}

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpsRequestInterceptor,
      multi: true,
    },
  ],
})
export class InterceptorModule {}

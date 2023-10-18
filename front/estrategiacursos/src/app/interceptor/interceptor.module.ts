import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable, NgModule } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../service/login.service';

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

    if (this.loginService.getToken()) {
      return next
        .handle(
          request.clone({
            setHeaders: {
              Authorization: this.loginService.getToken(),
              'Content-Type': 'application/json',
            },
          })
        )
        .pipe() as Observable<HttpEvent<any>>;
    } else {
      return next
        .handle(
          request.clone({
            setHeaders: {
              'Content-Type': 'application/json',
            },
          })
        )
        .pipe() as Observable<HttpEvent<any>>;
    }
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

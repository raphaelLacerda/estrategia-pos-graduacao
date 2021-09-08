import { LoginService } from './service/login.service';
import { Injectable  } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private loginService: LoginService,
  ) {}

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // if (this.loginService.isLogado()) {
    //   return true;
    // }
    // this.router.navigate(['/login']);
    // return false;
    return true;
  }
}

import { Injectable } from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor {

  constructor(
    private authenticationService: AuthenticationService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authHeaderString = this.authenticationService.getAuthenticatedToken();
    const username = this.authenticationService.getAuthenticatedUser();

    if (authHeaderString && username) {
      req = req.clone( {
        setHeaders : {
          Authorization : authHeaderString
        }
        }
      );
    }
    return next.handle(req);
  }
}

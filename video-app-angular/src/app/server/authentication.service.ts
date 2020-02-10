import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {VideoBasicInformation} from './videoappdatabase.service';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticaterUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  /** test */
  getTest() {
    return this.http.get<AuthenticationBean>('http://localhost:8100/hello-world-bean');
  }

  executeJWTAuthenticationService(username, password) {
    return this.http.post<any>(
      `http://localhost:8100/authenticate`, {
        username,
        password
      }).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
            return data;
          }
        )
    );
  }

  getAuthenticatedUser() { return sessionStorage.getItem(AUTHENTICATED_USER); }

  getAuthenticatedToken() { return sessionStorage.getItem(TOKEN); }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER);
    let token = sessionStorage.getItem(TOKEN);
    return !(user === null || token === null);
  }

  logout() {
    sessionStorage.removeItem(AUTHENTICATED_USER);
    sessionStorage.removeItem(TOKEN);
  }
}

export class AuthenticationBean {
  constructor(public message: string) { }
}

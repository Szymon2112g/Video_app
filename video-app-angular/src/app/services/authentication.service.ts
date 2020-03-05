import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {GetSubscriptionsUser} from './model/GetSubscriptionsUser.model';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticaterUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  getSubscriptionsUser() {
    const url = `http://localhost:8100/get-subscription/` + this.getAuthenticatedUser();
    return this.http.get<GetSubscriptionsUser[]>(url);
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
    const user = sessionStorage.getItem(AUTHENTICATED_USER);
    const token = sessionStorage.getItem(TOKEN);
    return !(user === null || token === null);
  }

  logout() {
    sessionStorage.removeItem(AUTHENTICATED_USER);
    sessionStorage.removeItem(TOKEN);
  }
}

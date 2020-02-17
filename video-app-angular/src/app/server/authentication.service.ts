import { Injectable } from '@angular/core';
import {HttpClient, HttpEventType, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {VideoToSend} from './model/VideoToSend.model';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticaterUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient,
  ) { }

  sendVideoToDataBase(videoToSend: VideoToSend) {
    const url = `http://localhost:8100/sendvideotodb`;
    return this.http.post<any>(url, videoToSend);
  }

  sendVideoFile(fileToUpload: File) {
    const url = `http://localhost:8100/sendvideofile`;
    const formData: FormData = new FormData();

    let fileName = this.getAuthenticatedUser() + '_' + fileToUpload.name;
    formData.append('file', fileToUpload, fileName);

    return this.http.post<any>(url, formData);
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

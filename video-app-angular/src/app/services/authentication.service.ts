import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {VideoToSend} from './model/VideoToSend.model';
import {AddReviewInformation} from './model/AddReviewInformation.model';
import {GetSubscriptionsUser} from './model/GetSubscriptionsUser.model';
import {AddSubscriptionsUser} from './model/AddSubscriptionUser.model';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticaterUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient,
  ) { }

  addReview(videoId: number, comment: string) {
      const url = `http://localhost:8100/addreview/`;

      let addReviewInformation = new AddReviewInformation('','',0);
      addReviewInformation.comment = comment;
      addReviewInformation.email = this.getAuthenticatedUser();
      addReviewInformation.videoId = videoId;

      return this.http.post<any>(url, addReviewInformation);
  }

  sendVideoToDataBase(videoToSend: VideoToSend) {
    const url = `http://localhost:8100/sendvideotodb`;
    return this.http.post<any>(url, videoToSend);
  }

  sendFile(fileToUpload: File, fileType: string) {
    const url = `http://localhost:8100/sendvideofile`;
    const formData: FormData = new FormData();

    let fileName = this.getAuthenticatedUser() + '_' + fileType + '_' + fileToUpload.name;

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

  addLikeToVideo(id: number) {
    const url = `http://localhost:8100/addliketovideo`;
    return this.http.post<any>(url, {id});
  }

  addDislikeToVideo(id: number) {
    const url = `http://localhost:8100/adddisliketovideo`;
    return this.http.post<any>(url, {id});
  }

  getSubscriptionsUser() {
    const url = `http://localhost:8100/getsubscription/` + this.getAuthenticatedUser();
    return this.http.get<GetSubscriptionsUser[]>(url);
  }

  addSubscriptionsUser(videoId: number) {
    const url = `http://localhost:8100/addsubscription`;

    let addSubscriptionsUser = new AddSubscriptionsUser('',0);
    addSubscriptionsUser.email = this.getAuthenticatedUser();
    addSubscriptionsUser.videoId = videoId;

    return this.http.post<any>(url, addSubscriptionsUser);
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

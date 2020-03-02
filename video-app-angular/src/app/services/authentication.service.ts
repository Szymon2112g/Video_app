import { Injectable } from '@angular/core';
import {HttpClient, HttpEventType, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {VideoToSend} from './model/VideoToSend.model';
import {AddReviewInformation} from './model/AddReviewInformation.model';
import {GetSubscriptionsUser} from './model/GetSubscriptionsUser.model';
import {AddDisplayWithUser} from './model/AddDisplayWithUser.model';
import {VideoBasicInformation} from './model/VideoBasicInformation.model';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticaterUser';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
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

  sendFile(fileToUpload: File, fileType: string, address: string) {

    const url = address + '/sendfile';
    console.log('czy dobry adress ' + url);
    const formData: FormData = new FormData();

    let fileName = this.getAuthenticatedUser() + '_' + fileType + '_' + fileToUpload.name;

    formData.append('file', fileToUpload, fileName);

    return this.http.post<any>(url, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  getVideosFeedOnAuthorization(category: string, id: number) {
    const endpoint = `http://localhost:8100/get-video-feed/` + category + '/' + id;
    let httpParams = new HttpParams().set('email', this.getAuthenticatedUser());
    return this.http.get<VideoBasicInformation[]>(endpoint, { params: httpParams});
  }


  getAddressUrlFileServer() {
    const url = `http://localhost:8100/get-address-url-file-server`;
    return this.http.get<any>(url);
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

  addLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/addliketovideo`;
    return this.http.post<any>(url, {id: videoId, email: this.getAuthenticatedUser()});
  }

  addDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/adddisliketovideo`;
    return this.http.post<any>(url, {id: videoId, email: this.getAuthenticatedUser()});
  }

  subtractLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/subtractliketovideo`;
    return this.http.post<any>(url, {id: videoId, email: this.getAuthenticatedUser()});
  }

  subtractDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/subtractdisliketovideo`;
    return this.http.post<any>(url, {id: videoId, email: this.getAuthenticatedUser()});
  }

  isLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/isliketovideo`;
    let httpParams = new HttpParams()
      .set('id', videoId.toString())
      .set('email', this.getAuthenticatedUser());
    return this.http.get<boolean>(url, { params: httpParams});
  }

  isDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/isdisliketovideo`;
    let httpParams = new HttpParams()
      .set('id', videoId.toString())
      .set('email', this.getAuthenticatedUser());
    return this.http.get<boolean>(url, { params: httpParams});
  }

  getSubscriptionsUser() {
    const url = `http://localhost:8100/getsubscription/` + this.getAuthenticatedUser();
    return this.http.get<GetSubscriptionsUser[]>(url);
  }

  addSubscriptionsUser(id: number) {
    const url = `http://localhost:8100/addsubscription`;
    return this.http.post<any>(url, {email: this.getAuthenticatedUser(), userId: id});
  }

  subtractSubscriptionsUser(id: number) {
    const url = `http://localhost:8100/subtractsubscription`;
    return this.http.post<any>(url, {email: this.getAuthenticatedUser(), userId: id});
  }

  addDisplayWithUser(videoId: number) {
    const url = `http://localhost:8100/adddisplaywithuser`;

    const addDisplayWithUser = new AddDisplayWithUser(this.getAuthenticatedUser(), videoId);

    return this.http.post<any>(url, addDisplayWithUser);
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

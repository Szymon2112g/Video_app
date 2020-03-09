import { Injectable } from '@angular/core';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {ReviewInformation} from '../../services/models/ReviewInformation.model';
import {HttpClient, HttpParams} from '@angular/common/http';
import {AddReviewInformation} from '../../services/models/AddReviewInformation.model';
import {GetSubscriptionsUser} from '../../services/models/SubscriptionsUser.model';
import {AddDisplayWithUser} from '../../services/models/AddDisplayWithUser.model';
import {AuthenticationService} from '../../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ShowVideoActionsService {

  constructor(
    private http: HttpClient,
    private auth: AuthenticationService
  ) { }


  getVideo(id: number) {
    const endpoint = `http://localhost:8080/get-video/` + id;
    return this.http.get<VideoInformation>(endpoint);
  }

  getReviewFromVideoId(id: number) {
    const endpoint = `http://localhost:8080/get-reviews/` + id;
    return this.http.get<ReviewInformation[]>(endpoint);
  }

  addDisplay(id: number) {
    const url = `http://localhost:8080/add-display`;
    return this.http.post<any>(url, {id});
  }

  addReview(videoId: number, comment: string) {
    const url = `http://localhost:8100/add-review/`;
    const addReviewInformation = new AddReviewInformation( this.auth.getAuthenticatedUser(), comment, videoId);
    return this.http.post<any>(url, addReviewInformation);
  }

  addLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/add-like-to-video`;
    return this.http.post<any>(url, {id: videoId, email: this.auth.getAuthenticatedUser()});
  }

  addDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/add-dislike-to-video`;
    return this.http.post<any>(url, {id: videoId, email: this.auth.getAuthenticatedUser()});
  }

  subtractLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/subtract-like-to-video`;
    return this.http.post<any>(url, {id: videoId, email: this.auth.getAuthenticatedUser()});
  }

  subtractDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/subtract-dislike-to-video`;
    return this.http.post<any>(url, {id: videoId, email: this.auth.getAuthenticatedUser()});
  }

  isLikeToVideo(videoId: number) {
    const url = `http://localhost:8100/is-like-to-video`;
    const httpParams = new HttpParams()
      .set('id', videoId.toString())
      .set('email', this.auth.getAuthenticatedUser());
    return this.http.get<boolean>(url, { params: httpParams});
  }

  isDislikeToVideo(videoId: number) {
    const url = `http://localhost:8100/is-dislike-to-video`;
    const httpParams = new HttpParams()
      .set('id', videoId.toString())
      .set('email', this.auth.getAuthenticatedUser());
    return this.http.get<boolean>(url, { params: httpParams});
  }

  addSubscriptionsUser(id: number) {
    const url = `http://localhost:8100/add-subscription`;
    return this.http.post<any>(url, {email: this.auth.getAuthenticatedUser(), userId: id});
  }

  subtractSubscriptionsUser(id: number) {
    const url = `http://localhost:8100/subtract-subscription`;
    return this.http.post<any>(url, {email: this.auth.getAuthenticatedUser(), userId: id});
  }

  addDisplayWithUser(videoId: number) {
    const url = `http://localhost:8100/add-display-with-user`;
    const addDisplayWithUser = new AddDisplayWithUser(this.auth.getAuthenticatedUser(), videoId);
    return this.http.post<any>(url, addDisplayWithUser);
  }
}

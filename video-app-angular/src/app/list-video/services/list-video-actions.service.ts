import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {AuthenticationService} from '../../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ListVideoActionsService {

  constructor(
    private http: HttpClient,
    private auth: AuthenticationService
  ) { }

  getVideos(category: string) {
    const endpoint = `http://localhost:8080/get-video-information/` + category;
    return this.http.get<VideoInformation[]>(endpoint);
  }

  getVideosFeedOnAuthorization(category: string, id: number) {
    const endpoint = `http://localhost:8100/get-video-feed/` + category + '/' + id;
    const httpParams = new HttpParams().set('email', this.auth.getAuthenticatedUser());
    return this.http.get<VideoInformation[]>(endpoint, { params: httpParams});
  }

  getTipsByKey(key: string) {
    const url = `http://localhost:8080/search/tips/` + key;
    return this.http.get<string[]>(url);
  }

  getVideoByKey(key: string) {
      const url = `http://localhost:8080/search/search/` + key;
      return this.http.get<VideoInformation[]>(url);
  }

  getVideosFeedOffAuthorization(category: string) {
    const endpoint = `http://localhost:8080/get-video-feed/` + category;
    return this.http.get<VideoInformation[]>(endpoint);
  }
}

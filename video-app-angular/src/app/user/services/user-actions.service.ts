import { Injectable } from '@angular/core';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserActionsService {

  constructor(
    private http: HttpClient
  ) { }

  register(firstName, lastName, email, password) {
    const endpoint = `http://localhost:8080/register`;
    return this.http.post(endpoint, { firstName, lastName, email, password});
  }

  getUsernameById(userId: number) {
    const url = `http://localhost:8080/user/get-username/` + userId;
    return this.http.get(url, {responseType: 'text'});
  }

  getVideoByUserId(userId: number) {
    const url = `http://localhost:8080/user/get-video/` + userId;
    return this.http.get<VideoInformation[]>(url);
  }
}

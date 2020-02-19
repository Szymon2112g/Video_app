import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {VideoBasicInformation} from './model/VideoBasicInformation.model';
import {ReviewInformation} from './model/ReviewInformation.model';

@Injectable({
  providedIn: 'root'
})
export class VideoappdatabaseService {

  constructor(
    private http: HttpClient
  ) { }

  getAllVideo() {
    const endpoint = `http://localhost:8080/videobasicinformation`;
    return this.http.get<VideoBasicInformation[]>(endpoint);
  }

  register(firstName, lastName, email, password) {
    const endpoint = `http://localhost:8080/register`;
    return this.http.post(endpoint, { firstName, lastName, email, password});
  }

  getVideo(id: number) {
    const endpoint = `http://localhost:8080/getvideo/` + id;
    return this.http.get<VideoBasicInformation>(endpoint);
  }

  getReviewFromVideoId(id: number) {
    const endpoint = `http://localhost:8080/getreviews/` + id;
    return this.http.get<ReviewInformation[]>(endpoint);
  }

}

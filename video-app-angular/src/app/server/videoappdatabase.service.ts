import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

export class VideoBasicInformation {
  constructor(public id: number, public url: string,
              public title: string, public description: string,
              public firstName: string, public lastName: string,
              public display: number, public photoUrl: string ) {}
}

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
}

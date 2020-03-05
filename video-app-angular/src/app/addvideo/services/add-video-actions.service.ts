import { Injectable } from '@angular/core';
import {VideoToSend} from '../../services/model/VideoToSend.model';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AddVideoActionsService {

  constructor(
    private http: HttpClient,
    private auth: AuthenticationService
  ) { }

  sendFile(fileToUpload: File, fileType: string, address: string) {
    const url = address + '/send-file';
    const formData: FormData = new FormData();
    const fileName = this.auth.getAuthenticatedUser() + '_' + fileType + '_' + fileToUpload.name;
    formData.append('file', fileToUpload, fileName);
    return this.http.post<any>(url, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  sendVideoToDataBase(videoToSend: VideoToSend) {
    const url = `http://localhost:8100/send-video-to-db`;
    return this.http.post<any>(url, videoToSend);
  }

  getAddressUrlFileServer() {
    const url = `http://localhost:8100/get-address-url-file-server`;
    return this.http.get<any>(url);
  }
}

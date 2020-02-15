import { Component, OnInit } from '@angular/core';
import {VideoBasicInformation, VideoToSend} from '../server/videoappdatabase.service';
import {AuthenticationService} from '../server/authentication.service';
import {HttpEvent, HttpUploadProgressEvent} from '@angular/common/http';
import {Observable, pipe} from 'rxjs';
import {share, tap} from 'rxjs/operators';

@Component({
  selector: 'app-addvideo',
  templateUrl: './addvideo.component.html',
  styleUrls: ['./addvideo.component.css']
})

export class AddvideoComponent implements OnInit {

  videoToUpload: File = null;
  photoToUpload: File = null;

  videoUrl: string;
  videoToSend: VideoToSend = new VideoToSend(
    this.authentication.getAuthenticatedUser(),'cos','title','desc',1,'url');

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  handleFileInput(files: FileList) {
    this.videoToUpload = files.item(0);
  }

  sendVideoFile() {
    this.authentication.sendVideoFile(this.videoToUpload).subscribe(
      data => {
        this.videoUrl = data.odp;
        this.videoToSend.url = this.videoUrl;
      }
    );
  }

  sendVideoToDB() {
    this.authentication.sendVideoToDataBase(this.videoToSend).subscribe(
      data => {

      }
    );
  }


}

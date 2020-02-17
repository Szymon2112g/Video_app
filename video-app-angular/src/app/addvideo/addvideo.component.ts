import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../server/authentication.service';
import {VideoToSend} from '../server/model/VideoToSend.model';

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
        this.videoUrl = data.message;
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

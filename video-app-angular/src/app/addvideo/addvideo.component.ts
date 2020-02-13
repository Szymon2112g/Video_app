import { Component, OnInit } from '@angular/core';
import {VideoBasicInformation} from '../server/videoappdatabase.service';
import {AuthenticationService} from '../server/authentication.service';

@Component({
  selector: 'app-addvideo',
  templateUrl: './addvideo.component.html',
  styleUrls: ['./addvideo.component.css']
})

export class AddvideoComponent implements OnInit {

  videoToUpload: File = null;
  photoToUpload: File = null;

  videoBasicInformation: VideoBasicInformation;

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  handleFileInput(files: FileList) {
    this.videoToUpload = files.item(0);
  }

  sendVideo() {
    this.authentication.sendVideoFile(this.videoToUpload).subscribe(
      data => {
        //success
      }, error => {
        //error
      }
    );
  }


}

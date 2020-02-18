import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from '../server/authentication.service';
import {VideoToSend} from '../server/model/VideoToSend.model';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-addvideo',
  templateUrl: './addvideo.component.html',
  styleUrls: ['./addvideo.component.css']
})

export class AddvideoComponent implements OnInit {

  @ViewChild('f', { static: false }) addVideoForm: NgForm;

  videoToUpload: File = null;
  photoToUpload: File = null;

  videoUrl: string;
  photoUrl: string;
  title: string;
  description: string;

  isVideo = false;
  isPhoto = false;

  videoToSend: VideoToSend = new VideoToSend(
    this.authentication.getAuthenticatedUser(), '', '', '', '');

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
  }

  handleVideoInput(files: FileList) {
    this.videoToUpload = files.item(0);
  }

  handlePhotoInput(files: FileList) {
    this.photoToUpload = files.item(0);
  }

  sendVideoFile() {
    this.authentication.sendFile(this.videoToUpload, 'video').subscribe(
      data => {
        this.videoUrl = data.message;
        this.videoToSend.url = this.videoUrl;
        this.isVideo = true;
      }
    );
  }

  sendPhotoFile() {
    this.authentication.sendFile(this.photoToUpload, 'photo').subscribe(
      data => {
        this.photoUrl = data.message;
        this.videoToSend.photoUrl = this.photoUrl;
        this.isPhoto = true;
      }
    );
  }

  sendVideoToDB() {

    this.videoToSend.description = this.description;
    this.videoToSend.title = this.title;

    this.authentication.sendVideoToDataBase(this.videoToSend).subscribe(
      data => {

      }
    );
  }

}

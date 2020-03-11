import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {VideoToSend} from '../services/models/VideoToSend.model';
import {NgForm} from '@angular/forms';
import {map} from 'rxjs/operators';
import {HttpEventType} from '@angular/common/http';
import {AddVideoActionsService} from './services/add-video-actions.service';
import {Router} from '@angular/router';

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

  valueProgressVideo = 0;
  valueProgressImage = 0;

  videoToSend: VideoToSend = new VideoToSend(
    this.authentication.getAuthenticatedUser(), '', '', '', '');

  constructor(
    private router: Router,
    private authentication: AuthenticationService,
    private addVideoActions: AddVideoActionsService
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
    this.addVideoActions.sendFile(this.videoToUpload, 'video')
      .pipe(
        map(
          (event) => {
          switch (event.type) {

            case HttpEventType.UploadProgress:
              const progress = Math.round(100 * event.loaded / event.total);
              this.valueProgressVideo = progress;
              return { status: 'progress', message: event };

            case HttpEventType.Response:
              return event.body;

            default:
              return `Unhandled event: ${event.type}`;
          }
      })
    ).subscribe(
      data => {
        this.videoUrl = data.message;
      }, error => {

      }, () => {
        this.videoToSend.url = this.videoUrl;
        this.isVideo = true;
      }
    );
  }

  sendPhotoFile() {
    this.addVideoActions.sendFile(this.photoToUpload, 'photo')
      .pipe(
        map(
          (event) => {
          switch (event.type) {

            case HttpEventType.UploadProgress:
              const progress = Math.round(100 * event.loaded / event.total);
              this.valueProgressImage = progress;
              return { status: 'progress', message: event };

            case HttpEventType.Response:
              return event.body;

            default:
              return `Unhandled event: ${event.type}`;
          }
      })
    ).subscribe(
      data => {
        this.photoUrl = data.message;
      }, error => {

      }, () => {
        this.videoToSend.photoUrl = this.photoUrl;
        this.isPhoto = true;
      }
    );
  }

  sendVideoToDB() {

    this.videoToSend.description = this.description;
    this.videoToSend.title = this.title;

    this.addVideoActions.sendVideoToDataBase(this.videoToSend).subscribe(
      data => {
          this.router.navigate(['/']);
      }
    );
  }

}

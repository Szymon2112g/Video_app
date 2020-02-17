import { Component, OnInit } from '@angular/core';
import {VideoappdatabaseService, VideoBasicInformation} from '../server/videoappdatabase.service';
import {AuthenticationService} from '../server/authentication.service';

@Component({
  selector: 'app-startpage',
  templateUrl: './startpage.component.html',
  styleUrls: ['./startpage.component.css']
})
export class StartpageComponent implements OnInit {

  videoBasic: VideoBasicInformation[];

  constructor(
    private VideoAppDatabase: VideoappdatabaseService,
    private authentication: AuthenticationService
    ) { }

  ngOnInit() {
    this.getAllVideo();
  }

  getAllVideo() {
    this.VideoAppDatabase.getAllVideo().subscribe(
      response => {
        this.videoBasic = response;
      }
    );
  }
}

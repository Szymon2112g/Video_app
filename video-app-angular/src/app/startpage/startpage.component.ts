import { Component, OnInit } from '@angular/core';
import {VideoappdatabaseService, VideoBasicInformation} from '../server/videoappdatabase.service';
import {AuthenticationService} from '../server/authentication.service';

@Component({
  selector: 'app-startpage',
  templateUrl: './startpage.component.html',
  styleUrls: ['./startpage.component.css']
})
export class StartpageComponent implements OnInit {

  test = 'brak';

  videoBasic: VideoBasicInformation[];

  constructor(
    private VideoAppDatabase: VideoappdatabaseService,
    private authentication: AuthenticationService
    ) { }

  ngOnInit() {
    this.getAllVideo();
    this.getTest();
  }

  getTest() {
    this.authentication.getTest().subscribe(
      response => {
        this.test = response.message;
      }
    );
  }

  getAllVideo() {
    this.VideoAppDatabase.getAllVideo().subscribe(
      response => {
        this.videoBasic = response;
      }
    );
  }
}

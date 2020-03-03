import { Component, OnInit } from '@angular/core';
import {VideoappdatabaseService} from '../services/videoappdatabase.service';
import {VideoBasicInformation} from '../services/model/VideoBasicInformation.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-user-site',
  templateUrl: './user-site.component.html',
  styleUrls: ['./user-site.component.css']
})
export class UserSiteComponent implements OnInit {

  videoBasicInformation: VideoBasicInformation[];
  userName: string;
  userId: number;

  constructor(
    private videoDataBase: VideoappdatabaseService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      data => {
        console.log(data);
        this.userId = this.activatedRoute.snapshot.params['id'];
        this.getVideo();
        this.getUsername();
      }
    );
  }

  getVideo() {
    this.videoDataBase.getVideoByUserId(this.userId)
      .subscribe(
        data => {
          this.videoBasicInformation = data;
        }
      );
  }

  getUsername() {
    this.videoDataBase.getUsernameById(this.userId)
      .subscribe(
        data => {
          this.userName = data;
        }
      );
  }

}

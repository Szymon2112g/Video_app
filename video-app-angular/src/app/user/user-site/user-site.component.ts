import { Component, OnInit } from '@angular/core';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {ActivatedRoute} from '@angular/router';
import {UserActionsService} from '../services/user-actions.service';

@Component({
  selector: 'app-user-site',
  templateUrl: './user-site.component.html',
  styleUrls: ['./user-site.component.css']
})
export class UserSiteComponent implements OnInit {

  videoInformations: VideoInformation[];
  userName: string;
  userId: number;

  constructor(
    private userActions: UserActionsService,
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
    this.userActions.getVideoByUserId(this.userId)
      .subscribe(
        data => {
          this.videoInformations = data;
        }
      );
  }

  getUsername() {
    this.userActions.getUsernameById(this.userId)
      .subscribe(
        data => {
          this.userName = data;
        }
      );
  }
}

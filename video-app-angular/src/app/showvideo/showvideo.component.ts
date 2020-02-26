import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {VideoappdatabaseService} from '../services/videoappdatabase.service';
import {VideoBasicInformation} from '../services/model/VideoBasicInformation.model';
import {ReviewInformation} from '../services/model/ReviewInformation.model';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-showvideo',
  templateUrl: './showvideo.component.html',
  styleUrls: ['./showvideo.component.css']
})
export class ShowvideoComponent implements OnInit {

  id: number;
  video: VideoBasicInformation;
  sizeVideo = 8;
  reviews: ReviewInformation[];
  ownReview: string;

  constructor(
    private route: ActivatedRoute,
    private videoAppDB: VideoappdatabaseService,
    private auth: AuthenticationService
  ) { }

  ngOnInit() {
   this.id = this.route.snapshot.params['id'];

   this.getVideo();
   this.getReview();
   this.addView();
  }

  getVideo() {
    this.videoAppDB.getVideo(this.id)
      .subscribe(
        data => {
          this.video = data;
          console.log(this.video.url);
        }
      );
  }

  getReview() {
    this.videoAppDB.getReviewFromVideoId(this.id)
      .subscribe(
        data => {
          this.reviews = data;
        }
      );
  }

  changeSizeVideo(size: number) {
    this.sizeVideo = size;
  }

  addReview() {
    this.auth.addReview(this.id, this.ownReview)
      .subscribe(
        data => {
          this.getReview();
          this.ownReview = '';
        }
      );
  }

  LikeVideo() {
    this.auth.addLikeToVideo(this.id)
      .subscribe(
        data => {
        }
      );
  }

  DisLikeVideo() {
    this.auth.addDislikeToVideo(this.id)
      .subscribe(
        data => {
        }
      );
  }

  addView() {
    this.videoAppDB.addDisplay(this.id)
      .subscribe(
        data => {
        }
      );
  }

  addSubscribe() {
    this.auth.addSubscriptionsUser(this.id)
      .subscribe(
        data => {

        }
      );
  }
}

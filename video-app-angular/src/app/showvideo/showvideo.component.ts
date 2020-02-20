import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {VideoappdatabaseService} from '../server/videoappdatabase.service';
import {VideoBasicInformation} from '../server/model/VideoBasicInformation.model';
import {ReviewInformation} from '../server/model/ReviewInformation.model';
import {AuthenticationService} from '../server/authentication.service';

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
    this.auth.addViews(this.id)
      .subscribe(
        data => {
        }
      );
  }
}

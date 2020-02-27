import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {VideoappdatabaseService} from '../services/videoappdatabase.service';
import {VideoBasicInformation} from '../services/model/VideoBasicInformation.model';
import {ReviewInformation} from '../services/model/ReviewInformation.model';
import {AuthenticationService} from '../services/authentication.service';
import {GetSubscriptionsUser} from '../services/model/GetSubscriptionsUser.model';

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
  isSubscription: boolean;
  isLike: boolean;
  isDislike: boolean;

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
          this.checkSubscriptions();
          this.isDislikeToVideo();
          this.isLikeToVideo();
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
          this.setLikeView(true);
          this.video.likes++;
        }
      );
  }

  DisLikeVideo() {
    this.auth.addDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(true);
          this.video.dislikes++;
        }
      );
  }

  addView() {
    if(this.auth.isUserLoggedIn()) {
      this.auth.addDisplayWithUser(this.id)
        .subscribe(
          data => {
          }
        )
    }
    else {
      this.videoAppDB.addDisplay(this.id)
        .subscribe(
          data => {
          }
        );
    }
  }

  addSubscribe() {
    this.auth.addSubscriptionsUser(this.video.userId)
      .subscribe(
        data => {
          this.setSubscriptionView(true);
        }
      );
  }

  subtractSubscribe() {
    this.auth.subtractSubscriptionsUser(this.video.userId)
      .subscribe(
        data => {
          this.setSubscriptionView(false);
        }
      );
  }

  checkSubscriptions() {
    this.auth.getSubscriptionsUser()
      .subscribe(
        data => {
          for (let tmp of data) {
            if(this.video.userId === tmp.userId) {
              this.setSubscriptionView(true);
              return;
            }
          }
        }
      );
  }

  setSubscriptionView(isSubscribe: boolean) {
    this.isSubscription = isSubscribe;
  }

  isLikeToVideo() {
      this.auth.isLikeToVideo(this.id)
       .subscribe(
        data => {
          this.setLikeView(data);
       }
    );
  }

  isDislikeToVideo() {
    this.auth.isDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(data);
        }
      );
  }

  subtractLikeToVideo() {
    this.auth.subtractLikeToVideo(this.id)
      .subscribe(
        data => {
          this.setLikeView(false);
          this.video.likes--;
        }
      );
  }

  subtractDislikeToVideo() {
    this.auth.subtractDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(false);
          this.video.dislikes--;
        }
      );
  }

  setLikeView(isLike: boolean) {
    this.isLike = isLike;
  }

  setDislikeView(isDislike: boolean) {
    this.isDislike = isDislike;
  }

}

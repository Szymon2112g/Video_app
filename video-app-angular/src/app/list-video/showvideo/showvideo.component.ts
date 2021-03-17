import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {ReviewInformation} from '../../services/models/ReviewInformation.model';
import {AuthenticationService} from '../../services/authentication.service';
import {ShowVideoActionsService} from '../services/show-video-actions.service';

@Component({
  selector: 'app-showvideo',
  templateUrl: './showvideo.component.html',
  styleUrls: ['./showvideo.component.css']
})
export class ShowvideoComponent implements OnInit {

  id: number;
  videoInformation: VideoInformation;
  sizeVideo = 8;
  reviews: ReviewInformation[];
  ownReview: string;
  isSubscription: boolean;
  isLike: boolean;
  isDislike: boolean;

  constructor(
    private route: ActivatedRoute,
    private showVideoActions: ShowVideoActionsService,
    public auth: AuthenticationService
  ) { }

  ngOnInit() {
   this.id = this.route.snapshot.params['id'];

   this.getVideo();
   this.getReview();
   this.addView();
  }

  getVideo() {
    this.showVideoActions.getVideo(this.id)
      .subscribe(
        data => {
          this.videoInformation = data;
          this.loadDataForAuthenticatedUser();
        }
      );
  }

  loadDataForAuthenticatedUser() {
    if (this.auth.isUserLoggedIn()) {
      this.checkSubscriptions();
      this.isDislikeToVideo();
      this.isLikeToVideo();
    }
  }

  getReview() {
    this.showVideoActions.getReviewFromVideoId(this.id)
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
    this.showVideoActions.addReview(this.id, this.ownReview)
      .subscribe(
        data => {
          this.getReview();
          this.ownReview = '';
        }
      );
  }

  LikeVideo() {
    this.showVideoActions.addLikeToVideo(this.id)
      .subscribe(
        data => {
          this.setLikeView(true);
          this.videoInformation.likes++;
        }
      );
  }

  DisLikeVideo() {
    this.showVideoActions.addDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(true);
          this.videoInformation.dislikes++;
        }
      );
  }

  addView() {
    if(this.auth.isUserLoggedIn()) {
      this.showVideoActions.addDisplayWithUser(this.id)
        .subscribe(
          data => {
          }
        );
    } else {
      this.showVideoActions.addDisplay(this.id)
        .subscribe(
          data => {
          }
        );
    }
  }

  addSubscribe() {
    this.showVideoActions.addSubscriptionsUser(this.videoInformation.userId)
      .subscribe(
        data => {
          this.setSubscriptionView(true);
        }
      );
  }

  subtractSubscribe() {
    this.showVideoActions.subtractSubscriptionsUser(this.videoInformation.userId)
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
            if(this.videoInformation.userId === tmp.userId) {
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
      this.showVideoActions.isLikeToVideo(this.id)
       .subscribe(
        data => {
          this.setLikeView(data);
       }
    );
  }

  isDislikeToVideo() {
    this.showVideoActions.isDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(data);
        }
      );
  }

  subtractLikeToVideo() {
    this.showVideoActions.subtractLikeToVideo(this.id)
      .subscribe(
        data => {
          this.setLikeView(false);
          this.videoInformation.likes--;
        }
      );
  }

  subtractDislikeToVideo() {
    this.showVideoActions.subtractDislikeToVideo(this.id)
      .subscribe(
        data => {
          this.setDislikeView(false);
          this.videoInformation.dislikes--;
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

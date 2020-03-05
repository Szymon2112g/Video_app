import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoInformation} from '../../services/model/VideoInformation.model';
import {AuthenticationService} from '../../services/authentication.service';
import {ListVideoActionsService} from '../services/list-video-actions.service';

@Component({
  selector: 'app-vertical-list-video',
  templateUrl: './vertical-list-video.component.html',
  styleUrls: ['./vertical-list-video.component.css']
})
export class VerticalListVideoComponent implements OnInit {

  category: string;
  search: string;

  categoryNameToView: string;
  isBrowser = false;
  canLoadMoreVideo = false;

  videoInformations: VideoInformation[];
  idLoad: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private auth: AuthenticationService,
    private listVideoActions: ListVideoActionsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      data => {
        this.getVideoFromCategory();
      }
    );
  }

  getVideoFromCategory() {
    this.category = this.activatedRoute.snapshot.params['category'];
    this.idLoad = 0;
    this.canLoadMoreVideo = true;

    switch (this.category) {
      case 'ontime':
        this.prepareSiteWithoutAuthorization();
        this.categoryNameToView = 'Na czasie';
        this.canLoadMoreVideo = false;
        break;
      case 'subscription':
        this.prepareSiteWithAuthorization();
        this.categoryNameToView = 'Subskrypcje';
        break;
      case 'history':
        this.prepareSiteWithAuthorization();
        this.categoryNameToView = 'Historia';
        break;
      case 'liked':
        this.prepareSiteWithAuthorization();
        this.categoryNameToView = 'Polubione';
        break;
      default:
        this.category = 'ontime';
        this.prepareSiteWithoutAuthorization();
        this.categoryNameToView = 'Na czasie';
        this.canLoadMoreVideo = false;
        break;
    }
  }

  prepareSiteWithAuthorization() {
    this.isBrowser = false;
    this.idLoad++;

    if (!this.auth.isUserLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    this.listVideoActions.getVideosFeedOnAuthorization(this.category, this.idLoad).subscribe(
      data => {
        this.videoInformations = data;
      }, error => {

      }, () => {

      }
    );
  }

  prepareSiteWithoutAuthorization() {
    this.isBrowser = false;
    this.idLoad++;

    this.listVideoActions.getVideosFeedOffAuthorization(this.category).subscribe(
      data => {
        this.videoInformations = data;
      }, error => {

      }, () => {

      }
    );
  }

  addData() {
    this.isBrowser = false;
    this.idLoad++;
    this.listVideoActions.getVideosFeedOnAuthorization(this.category, this.idLoad).subscribe(
      data => {
        this.videoInformations = this.videoInformations.concat(data);
      }, error => {

      }, () => {

      }
    );
  }
}

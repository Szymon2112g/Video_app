import {
  AfterContentChecked,
  AfterContentInit,
  AfterViewChecked,
  AfterViewInit,
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoappdatabaseService} from '../../services/videoappdatabase.service';
import {VideoBasicInformation} from '../../services/model/VideoBasicInformation.model';
import {AuthenticationService} from '../../services/authentication.service';
import {fromEvent, Observable, Subscription} from 'rxjs';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';

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

  videoBasicInformation: VideoBasicInformation[];
  idLoad: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private videoDataBase: VideoappdatabaseService,
    private auth: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      data => {
        console.log(data);
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

    this.auth.getVideosFeedOnAuthorization(this.category, this.idLoad).subscribe(
      data => {
        this.videoBasicInformation = data;
      }, error => {

      }, () => {
        console.log('complete');
      }
    );
  }

  prepareSiteWithoutAuthorization() {
    this.isBrowser = false;
    this.idLoad++;

    this.videoDataBase.getVideosFeedOffAuthorization(this.category).subscribe(
      data => {
        this.videoBasicInformation = data;
      }, error => {

      }, () => {
        console.log('complete');
      }
    );
  }

  addData() {
    this.isBrowser = false;
    this.idLoad++;
    this.auth.getVideosFeedOnAuthorization(this.category, this.idLoad).subscribe(
      data => {
        this.videoBasicInformation = this.videoBasicInformation.concat(data);
      }, error => {

      }, () => {
        console.log('complete');
      }
    );
  }

}

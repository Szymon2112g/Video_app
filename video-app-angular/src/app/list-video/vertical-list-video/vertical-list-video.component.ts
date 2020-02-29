import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {VideoappdatabaseService} from '../../services/videoappdatabase.service';
import {VideoBasicInformation} from '../../services/model/VideoBasicInformation.model';
import {AuthenticationService} from '../../services/authentication.service';

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

  videoBasicInformation: VideoBasicInformation[];
  videoToAdd: VideoBasicInformation[];
  idLoad: number;


  constructor(
    private activatedRoute: ActivatedRoute,
    private videoDataBase: VideoappdatabaseService,
    private auth: AuthenticationService
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

    switch (this.category) {
      case 'browser':
        this.prepareSiteForBrowser();
        this.categoryNameToView = 'Wyszukiwarka';
        break;
      case 'ontime':
        this.prepareSiteForConstantSite();
        this.categoryNameToView = 'Na czasie';
        break;
      case 'subscription':
        this.prepareSiteForConstantSite();
        this.categoryNameToView = 'Subskrypcje';
        break;
      case 'history':
        this.prepareSiteForConstantSite();
        this.categoryNameToView = 'Historia';
        break;
      case 'liked':
        this.prepareSiteForConstantSite();
        this.categoryNameToView = 'Polubione';
        break;
      default:
        this.prepareSiteForBrowser();
        this.categoryNameToView = 'Wyszukiwarka';
        break;
    }
  }

  prepareSiteForBrowser() {
    this.search = this.activatedRoute.snapshot.queryParams['search'];
    this.isBrowser = true;
  }

  prepareSiteForConstantSite() {
    this.isBrowser = false;
    this.idLoad++;
    this.auth.getVideosFeedOnAuthorization(this.category, this.idLoad).subscribe(
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

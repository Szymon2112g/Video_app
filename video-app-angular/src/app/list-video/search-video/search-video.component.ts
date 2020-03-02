import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {fromEvent, Subscription} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, min} from 'rxjs/operators';
import {VideoBasicInformation} from '../../services/model/VideoBasicInformation.model';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoappdatabaseService} from '../../services/videoappdatabase.service';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-search-video',
  templateUrl: './search-video.component.html',
  styleUrls: ['./search-video.component.css']
})
export class SearchVideoComponent implements OnInit, AfterViewInit, OnDestroy {

  @ViewChild('browser', {static: false}) browser: ElementRef;

  category: string;
  search = '';
  searchSub: Subscription;

  isBrowser = false;
  canLoadMoreVideo = false;

  videoBasicInformation: VideoBasicInformation[];
  tips: string[];
  idLoad: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private videoDataBase: VideoappdatabaseService,
    private auth: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.search = this.activatedRoute.snapshot.queryParams['search'];

    this.activatedRoute.queryParams.subscribe(
      data => {
        if (this.search !== '') {
          this.findVideoByKey();
        }
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

  ngAfterViewInit(): void {
    const terms$ = fromEvent<any>(this.browser.nativeElement, 'keyup')
      .pipe(
        filter(data =>  {
          return this.search.length >= 2 ? true : false;
        }),
        debounceTime(500),
        distinctUntilChanged()
      );

    this.searchSub = terms$.subscribe(
      data => {
        this.findTips();
      }
    );
  }

  ngOnDestroy(): void {
    this.searchSub.unsubscribe();
  }

  setSearch(search: string) {
    this.search = search;
    this.tips = null;
  }

  findTips() {
    this.videoDataBase.getTipsByKey(this.search).subscribe(
      data => {
        this.tips = data;
      }
    );
  }

  findVideoByKey() {
    this.videoDataBase.getVideoByKey(this.search)
      .subscribe(
        data => {
          this.videoBasicInformation = data;
        }
      );
  }
}

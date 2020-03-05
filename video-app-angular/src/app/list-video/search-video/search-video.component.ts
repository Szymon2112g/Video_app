import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {fromEvent, Subscription} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter} from 'rxjs/operators';
import {VideoInformation} from '../../services/model/VideoInformation.model';
import {ActivatedRoute} from '@angular/router';
import {ListVideoActionsService} from '../services/list-video-actions.service';

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

  videoInformations: VideoInformation[];
  tips: string[];
  idLoad: number;

  constructor(
    private activatedRoute: ActivatedRoute,
    private listVideoActions: ListVideoActionsService,
  ) { }

  ngOnInit() {

    if (this.activatedRoute.snapshot.queryParams['search']) {
      this.search = this.activatedRoute.snapshot.queryParams['search'];
    }

    this.activatedRoute.queryParams.subscribe(
      data => {
        this.findVideoByKey();
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
        console.log('complete');
      }
    );
  }

  ngAfterViewInit(): void {
    const terms$ = fromEvent<any>(this.browser.nativeElement, 'keyup')
      .pipe(
        filter(data =>  {
          return this.search.length >= 2;
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
    this.listVideoActions.getTipsByKey(this.search).subscribe(
      data => {
        this.tips = data;
      }
    );
  }

  findVideoByKey() {
    if (this.search.length >= 1) {
      this.listVideoActions.getVideoByKey(this.search)
        .subscribe(
          data => {
            this.videoInformations = data;
          }
        );
    }
  }

}

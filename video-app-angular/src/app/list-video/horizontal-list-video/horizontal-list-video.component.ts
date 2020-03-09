import {Component, Input, OnInit} from '@angular/core';
import {VideoInformation} from '../../services/models/VideoInformation.model';
import {ListVideoActionsService} from '../services/list-video-actions.service';

@Component({
  selector: 'app-horizontal-list-video',
  templateUrl: './horizontal-list-video.component.html',
  styleUrls: ['./horizontal-list-video.component.css']
})
export class HorizontalListVideoComponent implements OnInit {

  @Input() category: string;

  categoryName: string;

  videoInformations: VideoInformation[];

  constructor(
    private listVideoActions: ListVideoActionsService
  ) { }

  ngOnInit() {
    this.getVideoCategory();
  }

  getVideoCategory() {
    switch (this.category) {
      case 'most-views':
        this.categoryName = 'Najwiecej wyswietleń';
        this.getAllVideoCategory(this.category);
        break;
      case 'most-likes':
        this.categoryName = 'Najwiecej polubień';
        this.getAllVideoCategory(this.category);
        break;
      case 'most-dislikes':
        this.categoryName = 'Najwiecej niepolubień';
        this.getAllVideoCategory(this.category);
        break;
      case 'latest':
        this.categoryName = 'Najnowsze';
        this.getAllVideoCategory(this.category);
        break;
      default:
        this.categoryName = 'Najwiecej wyswietleń!';
        this.getAllVideoCategory('most-views');
        break;
    }
  }

  getAllVideoCategory(theCategory: string) {
    this.listVideoActions.getVideos(theCategory).subscribe(
      response => {
        this.videoInformations = response;
      }
    );
  }

}

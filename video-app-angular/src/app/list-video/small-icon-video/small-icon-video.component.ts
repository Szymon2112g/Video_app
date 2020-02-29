import {Component, Input, OnInit} from '@angular/core';
import {VideoBasicInformation} from '../../services/model/VideoBasicInformation.model';

@Component({
  selector: 'app-small-icon-video',
  templateUrl: './small-icon-video.component.html',
  styleUrls: ['./small-icon-video.component.css']
})
export class SmallIconVideoComponent implements OnInit {

  @Input() videoElement: VideoBasicInformation;
  constructor() { }

  ngOnInit() {
  }

}

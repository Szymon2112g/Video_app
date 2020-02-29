import {Component, Input, OnInit} from '@angular/core';
import {VideoBasicInformation} from '../../services/model/VideoBasicInformation.model';

@Component({
  selector: 'app-wide-icon-video',
  templateUrl: './wide-icon-video.component.html',
  styleUrls: ['./wide-icon-video.component.css']
})
export class WideIconVideoComponent implements OnInit {

  @Input() videoElement: VideoBasicInformation;

  constructor() { }

  ngOnInit() {
  }

}

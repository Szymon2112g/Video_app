import {Component, Input, OnInit} from '@angular/core';
import {VideoInformation} from '../../services/model/VideoInformation.model';

@Component({
  selector: 'app-small-icon-video',
  templateUrl: './small-icon-video.component.html',
  styleUrls: ['./small-icon-video.component.css']
})
export class SmallIconVideoComponent implements OnInit {

  @Input() videoInformation: VideoInformation;
  constructor() { }

  ngOnInit() {
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {VideoInformation} from '../../services/model/VideoInformation.model';

@Component({
  selector: 'app-wide-icon-video',
  templateUrl: './wide-icon-video.component.html',
  styleUrls: ['./wide-icon-video.component.css']
})
export class WideIconVideoComponent implements OnInit {

  @Input() videoInformation: VideoInformation;

  constructor() { }

  ngOnInit() {
  }

}

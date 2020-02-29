import { Component, OnInit } from '@angular/core';
import {VideoBasicInformation} from '../services/model/VideoBasicInformation.model';
import {VideoappdatabaseService} from '../services/videoappdatabase.service';

@Component({
  selector: 'app-startpage',
  templateUrl: './startpage.component.html',
  styleUrls: ['./startpage.component.css']
})
export class StartpageComponent implements OnInit {

  constructor( ) { }

  ngOnInit() {
  }

}

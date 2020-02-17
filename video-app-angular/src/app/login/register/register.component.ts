import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {VideoappdatabaseService} from '../../server/videoappdatabase.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName: string;
  lastName: string;
  email: string;
  password: string;

  constructor(
    private router: Router,
    private videoAppDB: VideoappdatabaseService
  ) { }

  ngOnInit() {
  }

  register() {
    this.videoAppDB.register(this.firstName, this.lastName, this.email, this.password).subscribe(
    data => {
      console.log(data);
      this.router.navigate(['']);
    },
      error => {
        console.log(error);
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../server/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'szymon2112g@o2.pl';
  password = '';
  errorMessage = 'Invalid Credentials';
  invalidLogin = false;

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  handleJWTAuthLogin() {
    this.authentication.executeJWTAuthenticationService(this.username, this.password)
      .subscribe(
        data => {
          console.log(data);
          this.invalidLogin = false;
          this.router.navigate(['']);
        },
        error => {
          console.log(error);
          this.invalidLogin = true;
        }
      )
  }
}

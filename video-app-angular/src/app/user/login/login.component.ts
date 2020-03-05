import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';
import {UserServiceService} from '../../services/user-service.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  signInForm: FormGroup;

  constructor(
    private authentication: AuthenticationService,
    private router: Router,
    private userService: UserServiceService
  ) { }

  ngOnInit() {
    this.signInForm = new FormGroup( {
      'userData': new FormGroup({
        'email': new FormControl(null, [Validators.required, Validators.email]),
        'password': new FormControl(null, [Validators.required, Validators.minLength(6)])
      })
    });
  }

  onSubmit() {
    if (this.signInForm.valid) {
      this.authentication.executeJWTAuthenticationService(
        this.signInForm.get('userData.email').value,
        this.signInForm.get('userData.password').value)
        .subscribe(
          data => {
            this.userService.activatedUser.next(this.authentication.getAuthenticatedUser());
            this.router.navigate(['']);
          },
          error => {
          }
        );
    }
  }
}

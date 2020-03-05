import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserActionsService} from '../services/user-actions.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  signOnForm: FormGroup;

  constructor(
    private router: Router,
    private userActions: UserActionsService
  ) { }

  ngOnInit() {
    this.signOnForm = new FormGroup( {
      'userData': new FormGroup({
        'firstName': new FormControl(null, [Validators.required, Validators.minLength(2)]),
        'lastName': new FormControl(null, [Validators.required, Validators.minLength(2)]),
        'email': new FormControl(null, [Validators.required, Validators.email]),
        'password': new FormControl(null, [Validators.required, Validators.minLength(6)])
      })
    });
  }

  onSubmit() {
    if (this.signOnForm.valid) {
      this.userActions.register(
      this.signOnForm.get('userData.firstName').value,
      this.signOnForm.get('userData.lastName').value,
      this.signOnForm.get('userData.email').value,
      this.signOnForm.get('userData.password').value)
      .subscribe(
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
}

import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserServiceService} from '../services/user-service.service';
import {Subscription} from 'rxjs';
import {AuthenticationService} from '../server/authentication.service';

@Component({
  selector: 'app-sidelist',
  templateUrl: './sidelist.component.html',
  styleUrls: ['./sidelist.component.css']
})
export class SidelistComponent implements OnInit, OnDestroy {

  userEmail = '';
  private userEmailSub: Subscription;

  constructor(
    private userService: UserServiceService,
    private auth: AuthenticationService
  ) { }

  ngOnInit() {
    if(this.auth.getAuthenticatedUser()) {
      this.userEmail = this.auth.getAuthenticatedUser();
    }
    else {
      this.userEmailSub = this.userService.activatedUser.subscribe(
        data => {
          this.userEmail = data;
        }
      );
    }
  }

  ngOnDestroy() {
    this.userEmailSub.unsubscribe();
  }

}

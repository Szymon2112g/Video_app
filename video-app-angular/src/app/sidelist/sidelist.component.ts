import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserServiceService} from '../services/user-service.service';
import {Subscription} from 'rxjs';
import {AuthenticationService} from '../services/authentication.service';
import {GetSubscriptionsUser} from '../services/model/GetSubscriptionsUser.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sidelist',
  templateUrl: './sidelist.component.html',
  styleUrls: ['./sidelist.component.css']
})
export class SidelistComponent implements OnInit, OnDestroy {

  subscriptionsUser: GetSubscriptionsUser[];
  userEmail = '';
  private userEmailSub: Subscription;

  constructor(
    private userService: UserServiceService,
    private auth: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    if(this.auth.getAuthenticatedUser()) {
      this.userEmail = this.auth.getAuthenticatedUser();
      this.getSubscriptions();
    }

    this.userEmailSub = this.userService.activatedUser.subscribe(
      data => {
        this.userEmail = data;
        this.getSubscriptions();
        }
    );
  }

  getSubscriptions() {
    if(this.userEmail !== '') {
      this.auth.getSubscriptionsUser()
        .subscribe(
          data => {
            this.subscriptionsUser = data;
          }
        );
    } else {
      this.subscriptionsUser = null;
    }
  }

  ngOnDestroy() {
    this.userEmailSub.unsubscribe();
  }

}

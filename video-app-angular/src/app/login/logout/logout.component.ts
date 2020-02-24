import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from '../../server/authentication.service';
import {Router} from '@angular/router';
import { Observable, Subscription} from 'rxjs';
import {UserServiceService} from '../../services/user-service.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit, OnDestroy {

  private routeSub: Subscription;
  secondsToRoute = 5;

  constructor(
    private auth: AuthenticationService,
    private router: Router,
    private userService: UserServiceService
  ) { }

  ngOnInit() {
    this.auth.logout();

    this.userService.activatedUser.next('');

    const intervalObservable = new Observable<number>( observer => {
      let count = 5;
      setInterval( () => {
        observer.next(count);
        if (count <= 0) {
          observer.complete();
        }
        if (count < 0) {
          observer.error('Count is below 0');
        }
        count--;
        }, 1000);
      }
    );

    this.routeSub = intervalObservable.subscribe(
      data => {
          this.secondsToRoute = data;
      }, error => {
          this.router.navigate(['']);
      }, () => {
        this.router.navigate(['']);
      }
    );
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
}

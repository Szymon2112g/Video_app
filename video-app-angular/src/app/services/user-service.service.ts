import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  activatedUser = new Subject<string>();
}

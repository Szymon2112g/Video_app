import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartpageComponent} from './startpage/startpage.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './login/register/register.component';
import {AddvideoComponent} from './addvideo/addvideo.component';
import {ShowvideoComponent} from './showvideo/showvideo.component';
import {LogoutComponent} from './login/logout/logout.component';
import {RouteGuardService} from './services/route-guard.service';


const routes: Routes = [
  {path: '', component: StartpageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'video/:id', component: ShowvideoComponent},
  {path: 'addvideo', component: AddvideoComponent, canActivate: [RouteGuardService]},
  {path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

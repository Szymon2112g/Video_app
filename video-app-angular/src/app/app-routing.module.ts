import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartpageComponent} from './startpage/startpage.component';
import {LoginComponent} from './user/login/login.component';
import {RegisterComponent} from './user/register/register.component';
import {AddvideoComponent} from './addvideo/addvideo.component';
import {ShowvideoComponent} from './list-video/showvideo/showvideo.component';
import {LogoutComponent} from './user/logout/logout.component';
import {RouteGuardService} from './services/route-guard.service';
import {VerticalListVideoComponent} from './list-video/vertical-list-video/vertical-list-video.component';
import {SearchVideoComponent} from './list-video/search-video/search-video.component';
import {UserSiteComponent} from './user/user-site/user-site.component';


const routes: Routes = [
  {path: '', component: StartpageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'video/:id', component: ShowvideoComponent},
  {path: 'feed/:category', component: VerticalListVideoComponent},
  {path: 'search', component: SearchVideoComponent},
  {path: 'user/:id', component: UserSiteComponent},
  {path: 'addvideo', component: AddvideoComponent, canActivate: [RouteGuardService]},
  {path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartpageComponent} from './startpage/startpage.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './login/register/register.component';
import {AddvideoComponent} from './addvideo/addvideo.component';


const routes: Routes = [
  {path: '', component: StartpageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'addvideo', component: AddvideoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

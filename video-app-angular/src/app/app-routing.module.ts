import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartpageComponent} from './startpage/startpage.component';
import {LoginComponent} from './login/login.component';


const routes: Routes = [
  {path: '', component: StartpageComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

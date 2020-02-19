import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { StartpageComponent } from './startpage/startpage.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {HttpIntercepterBasicAuthService} from './server/http/http-intercepter-basic-auth.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register/register.component';
import { AddvideoComponent } from './addvideo/addvideo.component';
import { ShowvideoComponent } from './showvideo/showvideo.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StartpageComponent,
    LoginComponent,
    RegisterComponent,
    AddvideoComponent,
    ShowvideoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

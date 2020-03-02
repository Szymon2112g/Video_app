import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { StartpageComponent } from './startpage/startpage.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {HttpIntercepterBasicAuthService} from './services/http/http-intercepter-basic-auth.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register/register.component';
import { AddvideoComponent } from './addvideo/addvideo.component';
import { ShowvideoComponent } from './showvideo/showvideo.component';
import { SidelistComponent } from './sidelist/sidelist.component';
import { LogoutComponent } from './login/logout/logout.component';
import { SmallIconVideoComponent } from './list-video/small-icon-video/small-icon-video.component';
import { HorizontalListVideoComponent } from './list-video/horizontal-list-video/horizontal-list-video.component';
import { VerticalListVideoComponent } from './list-video/vertical-list-video/vertical-list-video.component';
import { WideIconVideoComponent } from './list-video/wide-icon-video/wide-icon-video.component';
import { SearchVideoComponent } from './list-video/search-video/search-video.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StartpageComponent,
    LoginComponent,
    RegisterComponent,
    AddvideoComponent,
    ShowvideoComponent,
    SidelistComponent,
    LogoutComponent,
    SmallIconVideoComponent,
    HorizontalListVideoComponent,
    VerticalListVideoComponent,
    WideIconVideoComponent,
    SearchVideoComponent
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

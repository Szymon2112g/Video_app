import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { StartpageComponent } from './startpage/startpage.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpIntercepterBasicAuthService} from './services/http/http-intercepter-basic-auth.service';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { AddvideoComponent } from './addvideo/addvideo.component';
import { ShowvideoComponent } from './list-video/showvideo/showvideo.component';
import { SidelistComponent } from './sidelist/sidelist.component';
import { LogoutComponent } from './user/logout/logout.component';
import { SmallIconVideoComponent } from './list-video/small-icon-video/small-icon-video.component';
import { HorizontalListVideoComponent } from './list-video/horizontal-list-video/horizontal-list-video.component';
import { VerticalListVideoComponent } from './list-video/vertical-list-video/vertical-list-video.component';
import { WideIconVideoComponent } from './list-video/wide-icon-video/wide-icon-video.component';
import { SearchVideoComponent } from './list-video/search-video/search-video.component';
import { UserSiteComponent } from './user/user-site/user-site.component';

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
    SearchVideoComponent,
    UserSiteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

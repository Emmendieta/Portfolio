import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BannerComponent } from './components/banner/banner.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { SocialsNetsComponent } from './components/socials-nets/socials-nets.component';
import { HeaderComponent } from './components/header/header.component';
import { AboutComponent } from './components/about/about.component';
import {MatCardModule} from '@angular/material/card';
import { ExperienceComponent } from './components/experience/experience.component';
import { EducationComponent } from './components/education/education.component';
import { HardSoftComponent } from './components/hard-soft/hard-soft.component';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { ProyectsComponent } from './components/proyects/proyects.component';
import { FooterComponent } from './components/footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { Person } from './models/person.model';




@NgModule({
  declarations: [
    AppComponent,
    BannerComponent,
    SocialsNetsComponent,
    HeaderComponent,
    AboutComponent,
    ExperienceComponent,
    EducationComponent,
    HardSoftComponent,
    ProyectsComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    HttpClientModule,
    NgCircleProgressModule.forRoot({
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule, Component } from '@angular/core';
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
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { interceptorProvider } from './services/interceptor-service';
import { AddExperienceComponent } from './components/experience/add-experience.component';
import { EditExperienceComponent } from './components/experience/edit-experience.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { AddEducationComponent } from './components/education/add-education.component';
import { EditEducationComponent } from './components/education/edit-education.component';
import { AddHardSoftComponent } from './components/hard-soft/add-hard-soft.component';
import { EditHardSoftComponent } from './components/hard-soft/edit-hard-soft.component';
import { AddProyectComponent } from './components/proyects/add-proyect.component';
import { EditProyectComponent } from './components/proyects/edit-proyect.component';
import { EditSocialsNetsComponent } from './components/socials-nets/edit-socials-nets.component';
import { AddSocialsNetsComponent } from './components/socials-nets/add-socials-nets.component';
import { EditPersonComponent } from './components/person/edit-person.component';
import { AddPersonComponent } from './components/person/add-person.component';
import { NewUserComponent } from './components/login/new-user.component';


const appRoutes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "addExperience", component: AddExperienceComponent },
  {path: 'editExperience/:id', component: EditExperienceComponent},
  {path: 'addEducation', component: AddEducationComponent},
  {path: 'editEducation/:id', component: EditEducationComponent},
  {path: 'addSkill', component: AddHardSoftComponent},
  {path: 'editSkill/:id', component: EditHardSoftComponent},
  {path: 'addProyect', component: AddProyectComponent},
  {path: 'editProyect/:id', component: EditProyectComponent},
  {path: 'addSocialMedia', component: AddSocialsNetsComponent},
  {path: 'editSocialMedia/:id', component: EditSocialsNetsComponent},
  {path: 'editPerson/:id', component: EditPersonComponent},
  {path: 'newUser', component: NewUserComponent},
]


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
    HomeComponent,
    LoginComponent,
    AddExperienceComponent,
    EditExperienceComponent,
    AddEducationComponent,
    EditEducationComponent,
    AddHardSoftComponent,
    EditHardSoftComponent,
    AddProyectComponent,
    EditProyectComponent,
    EditSocialsNetsComponent,
    AddSocialsNetsComponent,
    EditPersonComponent,
    AddPersonComponent,
    NewUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    RouterModule.forRoot(
      appRoutes
    ),
    NgCircleProgressModule.forRoot({
    }),
    
  ],
  providers: [
    interceptorProvider,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

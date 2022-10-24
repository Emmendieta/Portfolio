import { Component, OnInit } from '@angular/core';
import { Experience } from 'src/app/models/experience.model';
import { TokenService } from 'src/app/services/token.service';
import { ExperienceService } from '../../services/experience.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})

export class ExperienceComponent implements OnInit {

  expe: Experience[] = [];


  constructor(private experienceService: ExperienceService, private tokenService: TokenService) { }

  logged = false;
  
  ngOnInit(): void {
    this.loadExperience();
    if (this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }

  loadExperience(): void{
    this.experienceService.getAllExperience().subscribe((result: any) =>{
      this.expe = result.data
    })
  }

  deleteExperience(id?: number){
    if(id != undefined){
      this.experienceService.deleteExperienceById(id).subscribe(
        data => {
          this.loadExperience();
        }, err => {
          alert("No se pudo borrar la experiencia");
        }
      )
    }
  }
}

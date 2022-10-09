import { Component, OnInit } from '@angular/core';
import { Experience } from 'src/app/models/experience.model';
import { ExperienceService } from '../../services/experience.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {
  exper: Experience[] = [];
  constructor(private experienceService: ExperienceService) { }

  ngOnInit(): void {
    this.loadExperience();
  }

  loadExperience(): void{
    this.experienceService.getAllExperience().subscribe((result: any) =>{
      this.exper = result.data
    })
  }

  deleteExperience(id?: number) {
    if(id != undefined){
      this.experienceService.deleteExperienceById(id).subscribe(
        data => {this.loadExperience();
        }, err => {
          alert("Error: no se pudo borrar");
        })
    }
  }
}

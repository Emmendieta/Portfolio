import { Component, OnInit } from '@angular/core';
import { ExperienceService } from '../../services/experience.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Experience } from '../../models/experience.model';


@Component({
  selector: 'app-edit-experience',
  templateUrl: './edit-experience.component.html',
  styleUrls: ['./edit-experience.component.css']
})
export class EditExperienceComponent implements OnInit {

  experience: Experience;
  private id: number;

  constructor(private experienceService: ExperienceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getExp();

  }

  getExp(){
    this.experienceService.getExperienceById(this.id).subscribe((result: any) =>{
      this.experience = result.data;
    })
  }

  updateExperience(): void {
    const id = this.route.snapshot.params['id'];
    this.experienceService.updateExperienceById(id, this.experience).subscribe(
      {next: (data) => {
        alert("Experience has been modify!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Experience has not been modify!");
        this.router.navigate(['']);
      }}
    )
  }

}

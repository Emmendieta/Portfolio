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

  editExp: Experience;

  constructor(private experienceService: ExperienceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.experienceService.getExperienceById(id).subscribe(
      {next: (data) => {
        this.editExp = data;
      },
      error: (err) => {
        alert("Error: Experience has not been modify!");
        this.router.navigate(['']);
      }}
    )
  }

  updateExperience(): void {
    const id = this.route.snapshot.params['id'];
    this.experienceService.updateExperienceById(id, this.editExp).subscribe(
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

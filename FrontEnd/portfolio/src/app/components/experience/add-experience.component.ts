import { Component, OnInit } from '@angular/core';
import { ExperienceService } from '../../services/experience.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Experience } from '../../models/experience.model';

@Component({
  selector: 'app-add-experience',
  templateUrl: './add-experience.component.html',
  styleUrls: ['./add-experience.component.css']
})
export class AddExperienceComponent implements OnInit {

  nameExp: string = "";
  titleExp: string = "";
  activityExp: string = "";
  dateStartExp: Date = new Date();
  dateEndExp: Date= new Date();
  imageExp: string = "";
  personId = 1;

  constructor(private experienceService: ExperienceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {  }

  createExperience(): void {
    const exp = new Experience(this.nameExp, this.titleExp, this.activityExp, this.dateStartExp, this.dateEndExp, this.imageExp, this.personId);
    this.experienceService.createExperience(exp).subscribe(
        {next: (data) => {
          alert("Experience has been added!");
          this.router.navigate(['']);
        },
        error: (err) => {
          alert("Error: Experience has not been added");
          this.router.navigate(['']);
        }}
     )
  }
}

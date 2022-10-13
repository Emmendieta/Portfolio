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

  nameExp: string =;
  titleExp: string;
  activityExp: string;
  dateStartExp: Date;
  dateEndExp: Date;
  imageExp: string;

  constructor(private experienceService: ExperienceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {  }

  setExperience(){
    this.experience.name = this.nameExp;
    this.experience.title = this.titleExp;
    this.experience.activity = this.activityExp;
    this.experience.dateStart = this.dateStartExp;
    this.experience.dateEnd = this.dateEndExp;
    this.experience.image = this.imageExp;
    this.experience.personId = 1;
  }

  createExperience(): void {
    this.setExperience();
    this.experienceService.createExperience(this.experience).subscribe(
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

import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { EducationService } from '../../services/education.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-education',
  templateUrl: './add-education.component.html',
  styleUrls: ['./add-education.component.css']
})
export class AddEducationComponent implements OnInit {

  nameEdu: string = "";
  titleEdu: string = "";
  descriptionEdu: string = "";
  dateStartEdu: Date = new Date();
  dateEndEdu: Date = new Date();
  imageEdu: string = "";
  personId: number = 1;

  constructor(private educationService: EducationService, private router: Router) { }

  ngOnInit(): void {

  }
  
  createEducation():void {
    const edu = new Education(this.nameEdu, this.titleEdu, this.descriptionEdu, this.dateStartEdu, this.dateEndEdu, this.imageEdu, this.personId);
    this.educationService.createEducation(edu).subscribe({
      next: (data) => {
        alert("Education has been added!");
        this.router.navigate(['']);
      },
      error:(err) => {
        alert("Error: Education has not been added");
        this.router.navigate(['']);
      }
    })
  }
}

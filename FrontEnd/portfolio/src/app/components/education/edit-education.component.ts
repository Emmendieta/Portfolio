import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { EducationService } from '../../services/education.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-education',
  templateUrl: './edit-education.component.html',
  styleUrls: ['./edit-education.component.css']
})
export class EditEducationComponent implements OnInit {

  education: Education;
  private id: number;

  constructor(private educationService: EducationService, private route: ActivatedRoute, private router: Router) { }
 
  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getEducation();
  }

  getEducation(){
    this.educationService.getEducationById(this.id).subscribe((result: any) => {
      this.education = result.data;
    })
  }
  
  updateEducation(): void{
    const id = this.route.snapshot.params['id'];
      this.educationService.updateEducationById(id, this.education).subscribe({
        next: (data) => {
          alert("Education has been modify!");
          this.router.navigate(['']);
        },
        error: (err) => {
          alert("Error: Education has not been modify!");
          this.router.navigate(['']);
        }
      })
  }
}

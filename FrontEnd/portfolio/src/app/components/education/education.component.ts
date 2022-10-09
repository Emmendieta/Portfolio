import { Component, OnInit } from '@angular/core';
import { EducationService } from 'src/app/services/education.service';
import { Education } from '../../models/education.model';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {
  edu: Education[] = [];
  constructor(private educationService: EducationService) { }

  ngOnInit(): void {
    this.loadEducation();
  }

  loadEducation(): void{
    this.educationService.getAllEducation().subscribe((result: any) => {
      this.edu = result.data;
    }) 
  }

  deleteEducation(id?: number){
    if(id != undefined){
      this.educationService.deleteEducationById(id).subscribe(
        data => {this.loadEducation();
        }, err => {
          alert("Error: no se pudo borrar");
        }
      )
    } 
  }

}

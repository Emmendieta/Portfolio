import { Component, OnInit } from '@angular/core';
import { EducationService } from 'src/app/services/education.service';
import { TokenService } from 'src/app/services/token.service';
import { Education } from '../../models/education.model';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  edu: Education[] = [];
  logged = false;
  constructor(private educationService: EducationService, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.loadEducation();
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
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

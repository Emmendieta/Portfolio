import { Component, OnInit } from '@angular/core';
import { ProyectService } from '../../services/proyect.service';
import { Router } from '@angular/router';
import { HardSoft } from '../../models/hardSoft.model';
import { Proyect } from '../../models/proyect.model';

@Component({
  selector: 'app-add-proyect',
  templateUrl: './add-proyect.component.html',
  styleUrls: ['./add-proyect.component.css']
})
export class AddProyectComponent implements OnInit {

  name: string;
  description: string;
  link: string;
  image: string;
  dateStart: Date;
  dateEnd: Date;
  personId: number = 1;
  constructor(private proyectService: ProyectService, private router: Router) { }

  ngOnInit(): void {
  }

  createSkill(): void {
    const skill = new Proyect(this.name, this.description, this.link, this.image, this.dateStart, this.dateEnd, this.personId);
    this.proyectService.createProyect(skill).subscribe({
      next: (data) => {
        alert("Skill has been added!");
        this.router.navigate(['']);
      }, 
      error: (err) => {
        alert("Skill has not been added!");
        this.router.navigate(['']);
      }
    })
  }
}

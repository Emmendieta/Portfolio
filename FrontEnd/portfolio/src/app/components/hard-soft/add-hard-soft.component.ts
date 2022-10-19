import { Component, OnInit } from '@angular/core';
import { HardSoftService } from '../../services/hard-soft.service';
import { Router } from '@angular/router';
import { HardSoft } from '../../models/hardSoft.model';

@Component({
  selector: 'app-add-hard-soft',
  templateUrl: './add-hard-soft.component.html',
  styleUrls: ['./add-hard-soft.component.css']
})
export class AddHardSoftComponent implements OnInit {

  name: string = "";
  percent: number;
  image: string = "";
  personId: number = 1;

  constructor(private hardSoftService: HardSoftService, private router: Router) { }

  ngOnInit(): void {
  }

  createHardSoft(): void {
    
    const hardSoftSkill = new HardSoft(this.name, this.percent, this.image, this.personId);
    this.hardSoftService.createHardSoft(hardSoftSkill).subscribe({
      next: (data) => {
        alert("Skill has been added!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Skill has not been added!");
        this.router.navigate(['']);
      }
    })
  }
}

import { Component, OnInit } from '@angular/core';
import { HardSoft } from '../../models/hardSoft.model';
import { HardSoftService } from '../../services/hard-soft.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-hard-soft',
  templateUrl: './edit-hard-soft.component.html',
  styleUrls: ['./edit-hard-soft.component.css']
})
export class EditHardSoftComponent implements OnInit {

  skill: HardSoft;
  private id: number;

  constructor(private hardSoftService: HardSoftService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getSkill();
  }

  getSkill() {
    this.hardSoftService.getHardSoftById(this.id).subscribe((result:any) => {
      this.skill = result.data;
    })
  }

  updateSkill(): void {
    const id = this.route.snapshot.params['id'];
    this.hardSoftService.updateHardSoftById(id, this.skill).subscribe({
      next: (data) => {
        alert("Skill has been modify!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Skill has not been modify!");
        this.router.navigate(['']);
      }
    })
  }
}

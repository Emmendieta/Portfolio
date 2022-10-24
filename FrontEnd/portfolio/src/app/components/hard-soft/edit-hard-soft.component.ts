import { Component, OnInit } from '@angular/core';
import { HardSoft } from '../../models/hardSoft.model';
import { HardSoftService } from '../../services/hard-soft.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-edit-hard-soft',
  templateUrl: './edit-hard-soft.component.html',
  styleUrls: ['./edit-hard-soft.component.css']
})
export class EditHardSoftComponent implements OnInit {

  logged = false;
  skill: HardSoft;
  private id: number;
  public skillForm;

  constructor(private hardSoftService: HardSoftService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para editar una Skill!");
      this.router.navigate(['']);
    }
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getSkill();
    this.initForm();
  }

  getSkill() {
    this.hardSoftService.getHardSoftById(this.id).subscribe((result:any) => {
      this.skill = result.data;
    })
  }

  updateSkill(): void {
    const id = this.route.snapshot.params['id'];
    this.skill.name = this.skillForm.get('name').value;
    this.skill.percent = this.skillForm.get('percent').value;
    this.skill.image = this.skillForm.get('image').value;
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

  initForm(){
    this.skillForm = this.formBuilder.group({
      name: ['', Validators.required],
      percent: ['', Validators.required],
      image: ['', Validators.required]
    })
  }
}

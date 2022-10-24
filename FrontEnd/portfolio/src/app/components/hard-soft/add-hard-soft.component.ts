import { Component, OnInit } from '@angular/core';
import { HardSoftService } from '../../services/hard-soft.service';
import { Router } from '@angular/router';
import { HardSoft } from '../../models/hardSoft.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-add-hard-soft',
  templateUrl: './add-hard-soft.component.html',
  styleUrls: ['./add-hard-soft.component.css']
})
export class AddHardSoftComponent implements OnInit {

  logged = false;
  name: string;
  percent: number;
  image: string;
  personId: number = 1;
  public skillForm;

  constructor(private hardSoftService: HardSoftService, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para agregar una Skill!");
      this.router.navigate(['']);
    }
    this.initForm();
  }

  createHardSoft(): void {
    
    const skill = new HardSoft(this.name = this.skillForm.get('name').value, 
      this.percent = this.skillForm.get('percent').value, 
      this.image = this.skillForm.get('image').value, 
      this.personId = 1);
    this.hardSoftService.createHardSoft(skill).subscribe({
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

  initForm(){
    this.skillForm = this.formBuilder.group({
      name: ['', Validators.required],
      percent: ['', Validators.required],
      image: ['', Validators.required]
    })
  }
}

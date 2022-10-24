import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../services/person.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Person } from '../../models/person.model';
import {  FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';
import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';


@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {

  logged= false;
  public person: Person;
  private personId: number;
  public personForm;
  
  constructor(private personService: PersonService, private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para editar a la persona!");
      this.router.navigate(['']);
    }
    this.personId = Number(this.route.snapshot.paramMap.get('id'));
    this.getPerson();
    this.initForm();
  }

  getPerson(){
    this.personService.getPersonId().subscribe((result: any) => {
      this.person = result.data
    })
  }

  updatePerson(): void {
    const personId = this.route.snapshot.params['id'];
    this.person.name = this.personForm.get('name').value;
    this.person.lastName = this.personForm.get('lastName').value;
    this.person.title = this.personForm.get('title').value;
    this.person.about = this.personForm.get('about').value;
    this.person.province = this.personForm.get('province').value;
    this.person.country = this.personForm.get('country').value;
    this.person.email = this.personForm.get('email').value;
    this.person.image = this.personForm.get('image').value;
    this.person.banner = this.personForm.get('banner').value;
    this.person.age = this.personForm.get('age').value;
    this.personService.updatePersonById(personId, this.person).subscribe({
      next: (data) => {
        alert("Person has been update!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Person has not been update!");
        this.router.navigate(['']);
      }
    })
  }

  initForm() {
    this.personForm = this.formBuilder.group({
      name: ['', Validators.required],
      lastName: ['', Validators.required],
      title: ['', Validators.required],
      about: ['', Validators.required],
      province: ['', Validators.required],
      country: ['', Validators.required],
      email: ['', Validators.required],
      image: ['', Validators.required],
      banner: ['', Validators.required],
      age: [new Date(), Validators.required]
    })
  }


}



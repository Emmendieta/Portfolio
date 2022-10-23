import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../services/person.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Person } from '../../models/person.model';
import { FormBuilder, Validators } from '@angular/forms';
import { yearsPerPage } from '@angular/material/datepicker';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {

  public person: Person = new Person("", "", new Date(),"","","","","", "", "");
  private personId: number;
  public formEditPerson;
  
  constructor(private personService: PersonService, private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'));
    this.getPerson();
  }

  getPerson(){
    this.personService.getPersonId().subscribe((result: any) => {
      this.person = result.data
    })
  }

  updatePerson(): void {
    const personId = this.route.snapshot.params['id'];
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
}

import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../services/person.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Person } from '../../models/person.model';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {

  person: Person;
  private personId: number;
  
  constructor(private personService: PersonService, private router: Router, private route: ActivatedRoute) { }

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
import { Component, OnInit } from '@angular/core';
import { Person } from '../../models/person.model';
import { PersonService } from '../../services/person.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {

  public person: Person = new Person("", "", new Date(),"","","","","", "", "");
  private personId: number;

  constructor(private personService: PersonService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'));
    this.getPerson();
  }

  public getPerson(){
    this.personService.getPersonId().subscribe((result: any) =>{
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

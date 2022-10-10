import { Component, OnInit } from '@angular/core';

import { Person } from 'src/app/models/person.model';
import { PersonService } from '../../services/person.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  public person = new Person();
  private personId: number;

  constructor(public personService: PersonService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'))
    this.getPerson()
  }

  getPerson(){
    this.personService.getPersonId(this.personId).subscribe((result: any) =>{
      this.person = result.data
    })
  }
}

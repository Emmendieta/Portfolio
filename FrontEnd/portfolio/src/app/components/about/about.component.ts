import { Component, OnInit } from '@angular/core';

import { Person } from 'src/app/models/person.model';
import { PersonService } from '../../services/person.service';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  public person: Person = new Person("", "", new Date(),"","","","","");
  private personId: number;
  logged = false;

  constructor(public personService: PersonService, private route: ActivatedRoute, private tokenService: TokenService) { }


  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'))
    this.getPerson()
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }

  public getPerson(){
    this.personService.getPersonId().subscribe((result: any) =>{
      this.person = result.data
    })
  }
}

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
  public person = new Person();
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

  getPerson(){
    this.personService.getPersonId(this.personId).subscribe((result: any) =>{
      this.person = result.data
    })
  }
}

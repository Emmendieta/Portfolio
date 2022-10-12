import { Component, OnInit } from '@angular/core';
import { PersonService } from 'src/app/services/person.service';
import { Person } from '../../models/person.model';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public person = new Person();
  private personId: number;
  logged = false;

  constructor(public personService: PersonService, private route: ActivatedRoute, private tokenService: TokenService) { }


  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'))
    this.getPerson();
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }

  getPerson(){
    this.personService.getPersonId(this.personId).subscribe((result: any) => {
      this.person = result.data
    })
  }
  
}

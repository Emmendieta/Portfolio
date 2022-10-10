import { Component, OnInit } from '@angular/core';
import { PersonService } from 'src/app/services/person.service';
import { Person } from '../../models/person.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public person = new Person();
  private personId: number;

  constructor(public personService: PersonService, private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.personId = Number(this.route.snapshot.paramMap.get('id'))
    this.getPerson();
  }

  getPerson(){
    this.personService.getPersonId(this.personId).subscribe((result: any) => {
      this.person = result.data
    })
  }
  
}

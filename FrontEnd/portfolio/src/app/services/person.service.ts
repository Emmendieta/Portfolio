import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Person } from '../models/person.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  baseURL = environment.URL + 'person/';

  public constructor(private http: HttpClient) { }

  getPersonId(id: number){
    return this.http.get(this.baseURL + `${id}`+ 1);
      //'{personId}?personId=' + 1);
  }

  updatePersonById(id: number, person: Person){
    return this.http.put(this.baseURL + "update/" + `${id}`, person);
  }

}

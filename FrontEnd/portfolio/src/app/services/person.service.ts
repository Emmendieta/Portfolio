import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Person } from '../models/person.model';
import { environment } from '../../environments/environment';
//import { environment } from 'src/environments/environment.prod';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  //baseURL = environment.URL + 'person/';
  baseURL = "https://portfolioemm.herokuapp.com/portfolio/v1/person/";
  public idNum = 1;

  public constructor(private http: HttpClient) { }

  getPersonId(){
    return this.http.get(this.baseURL+ + `${this.idNum}`)
  }

  updatePersonById(id: number, person: Person){
    return this.http.put(this.baseURL + 'update/' + `${id}`, person);
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from '../models/person.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  baseURL = environment.URL + 'person/';

  public constructor(private http: HttpClient) { }

  getPersonId(id: number){
    return this.http.get(this.baseURL + '{personId}?personId=' + 1);
  }

}

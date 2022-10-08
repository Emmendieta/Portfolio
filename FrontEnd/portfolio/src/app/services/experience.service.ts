import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  baseURL = environment.URL + "experience/"

  constructor(private http: HttpClient) { }

  getAllExperience(){
    return this.http.get(this.baseURL + "GetAll");
  }

  getExperienceById(id: number){
    return this.http.get(this.baseURL + `${id}`);
  }
}

import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Education } from '../models/education.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EducationService {
baseURL = environment.URL + "education/";
  constructor(private http: HttpClient) { }

  createEducation(education: Education){
    return this.http.post(this.baseURL + "createEducation", education);
  }

  getAllEducation(){
    return this.http.get(this.baseURL + "getAll");
  }

  getEducationById(id: number){
    return this.http.get(this.baseURL + `${id}`);
  }

  updateEducaction(id: number, education: Education){
    return this.http.put(this.baseURL + "update" + `${id}`, education);
  }

  deleteEducationById(id: number): Observable<any>{
    return this.http.delete(this.baseURL + "deleteById/"+ `${id}`);
  }
}

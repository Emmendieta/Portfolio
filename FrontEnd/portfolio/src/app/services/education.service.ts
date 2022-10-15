import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Education } from '../models/education.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EducationService {
baseURL = environment.URL + 'education/';
  constructor(private http: HttpClient) { }

  createEducation(education: Education){
    return this.http.post(this.baseURL + "createEducation", education);
  }

  getAllEducation(){
    return this.http.get(this.baseURL + "getAll");
  }

  getEducationById(id: number): Observable<Education>{
    return this.http.get<Education>(this.baseURL + `${id}`);
  }

  public updateEducationById(id: number, education: Education): Observable<any>{
    return this.http.put<any>(this.baseURL + "update/" + `${id}`, education);
  }

  deleteEducationById(id: number): Observable<any>{
    return this.http.delete(this.baseURL + "deleteById/"+ `${id}`);
  }
}

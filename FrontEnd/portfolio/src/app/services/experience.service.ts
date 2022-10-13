import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Experience } from '../models/experience.model';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  baseURL = environment.URL + 'experience/'

  constructor(private http: HttpClient) { }

  createExperience(experience: Experience): Observable<any>{
    return this.http.post<any>(this.baseURL + "createExperience", experience);
  }

  getAllExperience(){
    return this.http.get(this.baseURL + "GetAll");
  }

  getExperienceById(id: number): Observable<Experience>{
    return this.http.get<Experience>(this.baseURL + `${id}`);
  }

  updateExperienceById(id: number, experience:Experience){
    return this.http.put(this.baseURL + "update/" + `${id}`, experience);
  }

  public deleteExperienceById(id: number): Observable<any>{
    return this.http.delete<any>(this.baseURL + 'deleteById/'+ `${id}`);
  }
}

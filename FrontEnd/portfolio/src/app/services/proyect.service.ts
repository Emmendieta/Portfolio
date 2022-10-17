import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Proyect } from '../models/proyect.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ProyectService {
 //baseURL = environment.URL + 'proyect/'
 baseURL = "https://portfolioemm.herokuapp.com/portfolio/v1/proyect/";

  constructor(private http: HttpClient) { }

  createProyect(proyect: Proyect){
    return this.http.post(this.baseURL + 'createProyect', proyect);
  }

  getAllProyects(){
    return this.http.get(this.baseURL + 'getAll')
  }

  getProyectById(id: number){
    return this.http.get(this.baseURL + 'getById/' + `${id}`);
  }

  updateProyectById(id: number, proyect: Proyect){
    return this.http.put(this.baseURL + 'updateById/' + `${id}`, proyect);
  }

  deletePoryectById(id: number): Observable<any> {
    return this.http.delete(this.baseURL + 'deleteById/' + `${id}`);
  }
}

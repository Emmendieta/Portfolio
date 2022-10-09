import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { HardSoft } from '../models/hardSoft.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HardSoftService {
  baseURL = environment.URL + "hardAndSoft/";

  constructor(private http: HttpClient) { }

  createHardSoft(hardSoft: HardSoft){
    return this.http.post(this.baseURL + "createHardAndSoft", hardSoft);
  }

  getAllHardSoft(){
    return this.http.get(this.baseURL + "getAll");
  }

  getHardSoftById(id: number){
    return this.http.get(this.baseURL + `${id}`);
  }

  updateHardSoftById(id: number, hardSoft: HardSoft){
    return this.http.put(this.baseURL + "udpate" + `${id}`, hardSoft);
  }

  deleteHardSoftById(id: number): Observable<any>{
    return this.http.delete(this.baseURL + "deleteById/" + `${id}`)
  }
}

import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { SocialMedia } from '../models/socialMedia.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SocialMediaService {
  
  baseURL = environment.URL + "socialMedia/";
  constructor(private http: HttpClient) { }

  createSocial(socialMedia: SocialMedia){
    return this.http.post(this.baseURL + "createSocialMedia", socialMedia);
  }

  getSocialById(id: number){
    return this.http.get(this.baseURL + "findById/" + `${id}`);
  }

  getAllSocials(){
    return this.http.get(this.baseURL + "findAll/");
  }

  updateSocialById(id: number, socialMedia: SocialMedia){
    return this.http.put(this.baseURL + "updateSocialMedias/"  + `${id}`, socialMedia);
  }

  deleteSocialById(id: number): Observable<any> {
    return this.http.delete(this.baseURL + "deleteById/" + `${id}`);
  }
}

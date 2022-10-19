import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NewUser } from '../models/new-user';
import { Observable } from 'rxjs';
import { LoginUser } from '../models/login-user';
import { JwtDto } from '../models/jwt-dto';
import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseURL = environment.URL + 'auth/';

  constructor(private http: HttpClient) { }

  public newUser(newUser: NewUser): Observable<any>{
    return this.http.post<any>(this.baseURL + 'newUser', newUser);
  }

  public login(loginUser: LoginUser): Observable<JwtDto>{
    return this.http.post<JwtDto>(this.baseURL + 'login', loginUser);
  }
}

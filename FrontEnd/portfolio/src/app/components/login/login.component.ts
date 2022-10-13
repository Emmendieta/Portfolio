import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoginUser } from '../../models/login-user';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  logged = false;
  isLogginFail = false;
  loginUser!: LoginUser; 
  userName!: string;
  password! : string;
  rols: string[] = [];
  errorMessage!: string;

  constructor(private tokenService: TokenService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

    if(this.tokenService.getToken()){
      this.logged = true;
      this.isLogginFail = false;
      this.rols = this.tokenService.getAuthorities();
    }
  }

  onLogin(): void{
    this.loginUser = new LoginUser(this.userName, this.password);
    this.authService.login(this.loginUser).subscribe(data => {
      this.logged = true;
      this.isLogginFail = false;
      this.tokenService.setToken(data.token);
      this.tokenService.setUserName(data.nombreUsuario);
      this.tokenService.setAuthorities(data.authorities);
      this.rols = data.authorities;
      this.router.navigate(['']);
    }, err =>{
      this.logged = false;
      this.isLogginFail = true;
      this.errorMessage = err.error.message;
      console.log(this.errorMessage);
    }) 
  }
}
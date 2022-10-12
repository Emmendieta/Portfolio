import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-socials-nets',
  templateUrl: './socials-nets.component.html',
  styleUrls: ['./socials-nets.component.css']
})
export class SocialsNetsComponent implements OnInit {

  logged = false;
  textButtonLogged = "Login";
  textButtonNoLogged = "Logout";

  constructor(private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }
  login(){
    this.router.navigate(['/login']);
  }

  logOut():void {
    this.tokenService.logOut();
    window.location.reload();
  }
}

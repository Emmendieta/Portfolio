import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocialMediaService } from 'src/app/services/social-media.service';
import { TokenService } from 'src/app/services/token.service';
import { SocialMedia } from '../../models/socialMedia.model';

@Component({
  selector: 'app-socials-nets',
  templateUrl: './socials-nets.component.html',
  styleUrls: ['./socials-nets.component.css']
})
export class SocialsNetsComponent implements OnInit {

  socials: SocialMedia[] = [];
  logged = false;
  textButtonLogged = "Login";
  textButtonNoLogged = "Logout";

  constructor(private socialService: SocialMediaService, private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    this.loadSocials();
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

  loadSocials(){
    this.socialService.getAllSocials().subscribe((result: any) => {
      this.socials = result.data;
    })
  }
  deleteSocial(id?: number){
    if (id != undefined){
      this.socialService.deleteSocialById(id).subscribe(data => {
        this.loadSocials();
      }), err => {
        alert("Social Net has not been deleted!");
      }
    }
  }

}

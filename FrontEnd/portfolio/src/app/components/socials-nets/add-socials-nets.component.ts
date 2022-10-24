import { Component, OnInit } from '@angular/core';
import { SocialMediaService } from '../../services/social-media.service';
import { Router } from '@angular/router';
import { SocialMedia } from '../../models/socialMedia.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-add-socials-nets',
  templateUrl: './add-socials-nets.component.html',
  styleUrls: ['./add-socials-nets.component.css']
})
export class AddSocialsNetsComponent implements OnInit {

  logged = false;
  name: string;
  image: string;
  url: string;
  personId: number = 1;
  public addSocialForm;

  constructor(private socialMediaService: SocialMediaService, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: tiene que loguearse para agregar una Red Social!");
      this.router.navigate(['']);
    }
    this.initForm();
  }

  createSocial(): void {
    const social = new SocialMedia(
      this.name= this.addSocialForm.get('name').value,
      this.image = this.addSocialForm.get('image').value,
      this.url = this.addSocialForm.get('url').value, 
      this.personId)
    this.socialMediaService.createSocial(social).subscribe({
      next: (data) => {
        alert("Social Net has been added!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Social Net has not been added");
        this.router.navigate(['']);
      }
    })
  }

  initForm(){
    this.addSocialForm = this.formBuilder.group({
      name: ['', Validators.required],
      url: ['', Validators.required],
      image: ['', Validators.required]
    })
  }
}
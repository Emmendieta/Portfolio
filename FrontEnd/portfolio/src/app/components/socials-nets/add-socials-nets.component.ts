import { Component, OnInit } from '@angular/core';
import { SocialMediaService } from '../../services/social-media.service';
import { Router } from '@angular/router';
import { SocialMedia } from '../../models/socialMedia.model';

@Component({
  selector: 'app-add-socials-nets',
  templateUrl: './add-socials-nets.component.html',
  styleUrls: ['./add-socials-nets.component.css']
})
export class AddSocialsNetsComponent implements OnInit {

  name: string;
  image: string;
  url: string;
  personId: number = 1;
  constructor(private socialMediaService: SocialMediaService, private router: Router) { }

  ngOnInit(): void {
  }

  createSocial(): void {
    const social = new SocialMedia(this.name, this.image, this.url, this.personId);
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
}

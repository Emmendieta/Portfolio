import { Component, OnInit } from '@angular/core';
import { SocialMedia } from '../../models/socialMedia.model';
import { SocialMediaService } from '../../services/social-media.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-socials-nets',
  templateUrl: './edit-socials-nets.component.html',
  styleUrls: ['./edit-socials-nets.component.css']
})
export class EditSocialsNetsComponent implements OnInit {

  social: SocialMedia;
  private id: number;
  constructor(private socialMediaService: SocialMediaService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getSocial();
  }

  getSocial(): void {
    this.socialMediaService.getSocialById(this.id).subscribe((result: any) => {
      this.social = result.data;
    })
  }

  updateSocial(): void {
    const id = this.route.snapshot.params['id'];
    this.socialMediaService.updateSocialById(id, this.social).subscribe({
      next: (data) => {
        alert("Social Network has been udpate!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Social Network has not been update!");
        this.router.navigate(['']);
      }
    })
  }
}

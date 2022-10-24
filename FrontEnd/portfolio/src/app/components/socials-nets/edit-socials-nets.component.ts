import { Component, OnInit } from '@angular/core';
import { SocialMedia } from '../../models/socialMedia.model';
import { SocialMediaService } from '../../services/social-media.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-edit-socials-nets',
  templateUrl: './edit-socials-nets.component.html',
  styleUrls: ['./edit-socials-nets.component.css']
})
export class EditSocialsNetsComponent implements OnInit {

  logged = false;
  social: SocialMedia;
  private id: number;
  public socialForm;

  constructor(private socialMediaService: SocialMediaService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para editar una Red Social");
      this.router.navigate(['']);
    }
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getSocial();
    this.initForm();
  }

  getSocial(): void {
    this.socialMediaService.getSocialById(this.id).subscribe((result: any) => {
      this.social = result.data;
    })
  }

  updateSocial(): void {
    const id = this.route.snapshot.params['id'];
    this.social.name = this.socialForm.get('name').value;
    this.social.image = this.socialForm.get('image').value;
    this.social.url = this.socialForm.get('url').value;
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

  initForm(){
    this.socialForm = this.formBuilder.group({
      name: ['', Validators.required],
      url: ['', Validators.required],
      image: ['',Validators.required],
    })
  }
}

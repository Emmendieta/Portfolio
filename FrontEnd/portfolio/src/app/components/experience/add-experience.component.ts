import { Component, OnInit } from '@angular/core';
import { ExperienceService } from '../../services/experience.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Experience } from '../../models/experience.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-add-experience',
  templateUrl: './add-experience.component.html',
  styleUrls: ['./add-experience.component.css']
})
export class AddExperienceComponent implements OnInit {

  isHidden = false;
  logged = false;
  name: string;
  title: string;
  activity: string;
  dateStart: Date;
  dateEnd: Date;
  image: string;
  personId: number;
  public experienceForm;

  constructor(private experienceService: ExperienceService, private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void { 
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Debe loguearse para crear una Experiencia!");
      this.router.navigate(['']);
    }
    this.initForm();
   }

  createExperience(): void {
    const exp = new Experience(this.name = this.experienceForm.get('name').value, 
      this.title = this.experienceForm.get('title').value, 
      this.activity = this.experienceForm.get('activity').value, 
      this.dateStart = this.experienceForm.get('dateStart').value, 
      this.dateEnd = this.experienceForm.get('dateEnd').value, 
      this.image = this.experienceForm.get('image').value, 
      this.personId = 1);
    this.experienceService.createExperience(exp).subscribe(
        {next: (data) => {
          alert("Experience has been added!");
          this.router.navigate(['']);
        },
        error: (err) => {
          alert("Error: Experience has not been added");
          this.router.navigate(['']);
        }}
     )
  }

  initForm() {
    this.experienceForm = this.formBuilder.group({
      name: ['', Validators.required],
      activity: ['', Validators.required],
      title: ['', Validators.required],
      image: ['', Validators.required],
      dateStart: [new Date(), Validators.required],
      dateEnd: [new Date(), Validators.required],
      checked: false
    })
  }

  showHide(){
    this.isHidden = !this.isHidden;
    alert("Esta funcion estará disponible pronto en el Home");
  }
}

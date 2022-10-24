import { Component, OnInit } from '@angular/core';
import { ExperienceService } from '../../services/experience.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Experience } from '../../models/experience.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';


@Component({
  selector: 'app-edit-experience',
  templateUrl: './edit-experience.component.html',
  styleUrls: ['./edit-experience.component.css']
})
export class EditExperienceComponent implements OnInit {

  isHidden = false;
  logged = false;
  experience: Experience;
  private id: number;
  public experienceForm;

  constructor(private experienceService: ExperienceService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Debe loguearse para editar la Experiencia");
      this.router.navigate(['']);
    }
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getExp();
    this.initForm();

  }

  getExp(){
    this.experienceService.getExperienceById(this.id).subscribe((result: any) =>{
      this.experience = result.data;
    })
  }

  updateExperience(): void {
    const id = this.route.snapshot.params['id'];
    this.experience.name = this.experienceForm.get('name').value;
    this.experience.title = this.experienceForm.get('title').value;
    this.experience.activity = this.experienceForm.get('activity').value;
    this.experience.image = this.experienceForm.get('image').value;
    this.experience.dateStart = this.experienceForm.get('dateStart').value;
    this.experience.dateEnd = this.experienceForm.get('dateEnd').value;
    this.experienceService.updateExperienceById(id, this.experience).subscribe(
      {next: (data) => {
        alert("Experience has been modify!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: Experience has not been modify!");
        this.router.navigate(['']);
      }}
    )
  }

  initForm(){
    this.experienceForm = this.formBuilder.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      activity: ['', Validators.required],
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

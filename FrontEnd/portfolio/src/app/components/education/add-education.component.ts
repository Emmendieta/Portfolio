import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { EducationService } from '../../services/education.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-add-education',
  templateUrl: './add-education.component.html',
  styleUrls: ['./add-education.component.css']
})
export class AddEducationComponent implements OnInit {

  isHidden = false;
  logged = false;
  name: string;
  title: string;
  description: string;
  dateStart: Date;
  dateEnd: Date;
  image: string;
  personId: number;
  public educationForm;

  constructor(private educationService: EducationService, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para agregar una Educación!");
      this.router.navigate(['']);
    }
    this.initForm();

  }
  
  createEducation():void {
    const edu = new Education(this.name = this.educationForm.get('name').value, 
      this.title = this.educationForm.get('title').value, 
      this.description = this.educationForm.get('description').value, 
      this.dateStart = this.educationForm.get('dateStart').value, 
      this.dateEnd = this.educationForm.get('dateEnd').value, 
      this.image = this.educationForm.get('image').value, 
      this.personId = 1);
    this.educationService.createEducation(edu).subscribe({
      next: (data) => {
        alert("Education has been added!");
        this.router.navigate(['']);
      },
      error:(err) => {
        alert("Error: Education has not been added");
        this.router.navigate(['']);
      }
    })
  }

  initForm() {
    this.educationForm = this.formBuilder.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
      dateStart: [new Date(), Validators.required],
      dateEnd: [new Date(), Validators.required],
      image: ['', Validators.required],
      checked: false
    })
  }

  showHide(){
    this.isHidden = !this.isHidden;
    alert("Esta funcion estará disponible pronto en el Home");
  }
}

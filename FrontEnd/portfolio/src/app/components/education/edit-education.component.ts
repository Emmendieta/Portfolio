import { Component, OnInit } from '@angular/core';
import { Education } from '../../models/education.model';
import { EducationService } from '../../services/education.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-edit-education',
  templateUrl: './edit-education.component.html',
  styleUrls: ['./edit-education.component.css']
})
export class EditEducationComponent implements OnInit {

  isHidden = false;
  logged = false;
  education: Education;
  private id: number;
  public educationForm;

  constructor(private educationService: EducationService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }
 
  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Se tiene que loguear para editar una Educación!");
      this.router.navigate(['']);
    }
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getEducation();
    this.initForm();
  }

  getEducation(){
    this.educationService.getEducationById(this.id).subscribe((result: any) => {
      this.education = result.data;
    })
  }
  
  updateEducation(): void{
    const id = this.route.snapshot.params['id'];
    this.education.name = this.educationForm.get('name').value;
    this.education.title = this.educationForm.get('title').value;
    this.education.description = this.educationForm.get('description').value;
    this.education.image = this.educationForm.get('image').value;
    this.education.dateStart = this.educationForm.get('dateStart').value;
    this.education.dateEnd = this.educationForm.get('dateEnd').value;
      this.educationService.updateEducationById(id, this.education).subscribe({
        next: (data) => {
          alert("Education has been modify!");
          this.router.navigate(['']);
        },
        error: (err) => {
          alert("Error: Education has not been modify!");
          this.router.navigate(['']);
        }
      })
  }

  initForm(){
    this.educationForm = this.formBuilder.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
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

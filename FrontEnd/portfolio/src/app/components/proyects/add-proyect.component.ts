import { Component, OnInit } from '@angular/core';
import { ProyectService } from '../../services/proyect.service';
import { Router } from '@angular/router';
import { Proyect } from '../../models/proyect.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-add-proyect',
  templateUrl: './add-proyect.component.html',
  styleUrls: ['./add-proyect.component.css']
})
export class AddProyectComponent implements OnInit {

  isHidden = false;
  logged = false;
  name: string;
  description: string;
  link: string;
  image: string;
  dateStart: Date;
  dateEnd: Date;
  personId: number = 1;
  public proyectForm;

  constructor(private proyectService: ProyectService, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para crear un proyecto!");
      this.router.navigate(['']);
    }
    this.initForm();
  }

  createProyect(): void {
    const proyect = new Proyect(this.name = this.proyectForm.get('name').value,
    this.description = this.proyectForm.get('description').value,
    this.link = this.proyectForm.get('link').value,
    this.image = this.proyectForm.get('image').value, 
    this.dateStart = this.proyectForm.get('dateStart').value, 
    this.dateEnd = this.proyectForm.get('dateEnd').value, this.personId);
    this.proyectService.createProyect(proyect).subscribe({
      next: (data) => {
        alert("Proyect has been added!");
        this.router.navigate(['']);
      }, 
      error: (err) => {
        alert("Error: Proyect has not been added!");
        this.router.navigate(['']);
      }
    })
  }

  initForm() {
    this.proyectForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      link: ['', Validators.required],
      image: ['', Validators.required],
      dateStart: [new Date(), Validators.required],
      dateEnd: [new Date(), Validators.required]
    })
  }

  showHide(){
    this.isHidden = !this.isHidden;
    alert("Esta funcion estará disponible pronto en el Home");
  }
}

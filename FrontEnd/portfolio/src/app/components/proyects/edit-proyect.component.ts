import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProyectService } from '../../services/proyect.service';
import { Proyect } from '../../models/proyect.model';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-edit-proyect',
  templateUrl: './edit-proyect.component.html',
  styleUrls: ['./edit-proyect.component.css']
})
export class EditProyectComponent implements OnInit {

  isHidden = false;
  logged = false;
  proyect: Proyect;
  private id: number;
  public proyectForm;

  constructor(private proyectService: ProyectService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private token: TokenService) { }

  ngOnInit(): void {
    if(this.token.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
      alert("Error: Tiene que loguearse para editar un proyecto!");
      this.router.navigate(['']);
    }

    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getProyect();
    this.initForm();

  }

  getProyect() {
    this.proyectService.getProyectById(this.id).subscribe((reuslt: any) => {
      this.proyect = reuslt.data;
    })
  }

  updateProyect(): void {
    const id = this.route.snapshot.params['id'];
    this.proyect.name = this.proyectForm.get('name').value;
    this.proyect.description = this.proyectForm.get('description').value;
    this.proyect.link = this.proyectForm.get('link').value;
    this.proyect.image = this.proyectForm.get('image').value;
    this.proyect.dateStart = this.proyectForm.get('dateStart').value;
    this.proyect.dateEnd = this.proyectForm.get('dateEnd').value;
    this.proyectService.updateProyectById(id, this.proyect).subscribe({
      next: (data) => {
        alert("Proyect has been udpdate!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Proyect has not been update!");
        this.router.navigate(['']);
      }
    })
  }

  initForm(){
    this.proyectForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      link: ['', Validators.required],
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

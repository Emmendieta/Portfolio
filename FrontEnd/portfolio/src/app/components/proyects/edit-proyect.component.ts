import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProyectService } from '../../services/proyect.service';
import { Proyect } from '../../models/proyect.model';

@Component({
  selector: 'app-edit-proyect',
  templateUrl: './edit-proyect.component.html',
  styleUrls: ['./edit-proyect.component.css']
})
export class EditProyectComponent implements OnInit {

  proyect: Proyect;
  private id: number;

  constructor(private proyectService: ProyectService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getProyect();

  }

  getProyect() {
    this.proyectService.getProyectById(this.id).subscribe((reuslt: any) => {
      this.proyect = reuslt.data;
    })
  }

  updateProyect(): void {
    const id = this.route.snapshot.params['id'];
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
}

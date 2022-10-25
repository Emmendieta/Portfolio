import { DatePipe, formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/token.service';
import { Proyect } from '../../models/proyect.model';
import { ProyectService } from '../../services/proyect.service';

@Component({
  selector: 'app-proyects',
  templateUrl: './proyects.component.html',
  styleUrls: ['./proyects.component.css']
})
export class ProyectsComponent implements OnInit {

  proy: Proyect[] = [];
  logged = false;

  constructor(private proyectService: ProyectService, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.loadProyect();
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }

  loadProyect(){
    this.proyectService.getAllProyects().subscribe((result: any) => {
      this.proy = result.data;
    })
  }

  deleteProyect(id?: number){
    if(id != undefined){
      this.proyectService.deletePoryectById(id).subscribe(data => {
        this.loadProyect();
      }, err => {
        alert("Error: No se pudo eliminar el Proyecto seleccionado!");
      })
    }
  }
  
}

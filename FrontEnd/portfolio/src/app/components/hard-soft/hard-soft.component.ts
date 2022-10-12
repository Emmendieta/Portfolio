import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/token.service';
import { HardSoft } from '../../models/hardSoft.model';
import { HardSoftService } from '../../services/hard-soft.service';

@Component({
  selector: 'app-hard-soft',
  templateUrl: './hard-soft.component.html',
  styleUrls: ['./hard-soft.component.css']
})
export class HardSoftComponent implements OnInit {

  hys: HardSoft[] = [];
  logged = false;

  constructor(private hardSoftService: HardSoftService, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.loadHardSoft();
    if(this.tokenService.getToken()){
      this.logged = true;
    } else {
      this.logged = false;
    }
  }

  loadHardSoft(): void {
    this.hardSoftService.getAllHardSoft().subscribe((result: any) => {
      this.hys = result.data;
    })
  }

  deleteHardSoft(id?: number){
    if(id != undefined){
      this.hardSoftService.deleteHardSoftById(id).subscribe(data => {
        this.loadHardSoft();
      }, err => {
        alert("Error: No se pudo eliminar la Skill!");
      })
    }
  }
}

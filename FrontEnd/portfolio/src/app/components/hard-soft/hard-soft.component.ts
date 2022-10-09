import { Component, OnInit } from '@angular/core';
import { HardSoft } from '../../models/hardSoft.model';
import { HardSoftService } from '../../services/hard-soft.service';

@Component({
  selector: 'app-hard-soft',
  templateUrl: './hard-soft.component.html',
  styleUrls: ['./hard-soft.component.css']
})
export class HardSoftComponent implements OnInit {
  hys: HardSoft[] = [];

  constructor(private hardSoftService: HardSoftService) { }

  ngOnInit(): void {
    this.loadHardSoft();
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

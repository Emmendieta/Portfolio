import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSocialsNetsComponent } from './add-socials-nets.component';

describe('AddSocialsNetsComponent', () => {
  let component: AddSocialsNetsComponent;
  let fixture: ComponentFixture<AddSocialsNetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSocialsNetsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSocialsNetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

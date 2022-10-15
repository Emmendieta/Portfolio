import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSocialsNetsComponent } from './edit-socials-nets.component';

describe('EditSocialsNetsComponent', () => {
  let component: EditSocialsNetsComponent;
  let fixture: ComponentFixture<EditSocialsNetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditSocialsNetsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditSocialsNetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

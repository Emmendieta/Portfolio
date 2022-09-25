import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialsNetsComponent } from './socials-nets.component';

describe('SocialsNetsComponent', () => {
  let component: SocialsNetsComponent;
  let fixture: ComponentFixture<SocialsNetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialsNetsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SocialsNetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

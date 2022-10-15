import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHardSoftComponent } from './add-hard-soft.component';

describe('AddHardSoftComponent', () => {
  let component: AddHardSoftComponent;
  let fixture: ComponentFixture<AddHardSoftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddHardSoftComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddHardSoftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

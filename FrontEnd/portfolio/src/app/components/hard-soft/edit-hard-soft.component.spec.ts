import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHardSoftComponent } from './edit-hard-soft.component';

describe('EditHardSoftComponent', () => {
  let component: EditHardSoftComponent;
  let fixture: ComponentFixture<EditHardSoftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditHardSoftComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditHardSoftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

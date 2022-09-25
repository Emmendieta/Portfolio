import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarToolComponent } from './bar-tool.component';

describe('BarToolComponent', () => {
  let component: BarToolComponent;
  let fixture: ComponentFixture<BarToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BarToolComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BarToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

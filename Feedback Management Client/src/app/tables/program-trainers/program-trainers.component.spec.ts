import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramTrainersComponent } from './program-trainers.component';

describe('ProgramTrainersComponent', () => {
  let component: ProgramTrainersComponent;
  let fixture: ComponentFixture<ProgramTrainersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProgramTrainersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramTrainersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

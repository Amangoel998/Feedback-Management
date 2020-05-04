import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaiablefeedbacksComponent } from './avaiablefeedbacks.component';

describe('AvaiablefeedbacksComponent', () => {
  let component: AvaiablefeedbacksComponent;
  let fixture: ComponentFixture<AvaiablefeedbacksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvaiablefeedbacksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvaiablefeedbacksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

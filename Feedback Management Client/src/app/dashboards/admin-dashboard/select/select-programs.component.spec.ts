import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectProgramsComponent } from './select.component';

describe('SelectProgramsComponent', () => {
  let component: SelectProgramsComponent;
  let fixture: ComponentFixture<SelectProgramsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectProgramsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseProgramsComponent } from './course-programs.component';

describe('CourseProgramsComponent', () => {
  let component: CourseProgramsComponent;
  let fixture: ComponentFixture<CourseProgramsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseProgramsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

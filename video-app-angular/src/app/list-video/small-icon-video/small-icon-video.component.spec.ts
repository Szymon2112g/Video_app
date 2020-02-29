import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallIconVideoComponent } from './small-icon-video.component';

describe('SmallIconVideoComponent', () => {
  let component: SmallIconVideoComponent;
  let fixture: ComponentFixture<SmallIconVideoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmallIconVideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmallIconVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

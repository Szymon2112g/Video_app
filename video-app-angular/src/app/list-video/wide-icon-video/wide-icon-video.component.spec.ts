import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { WideIconVideoComponent } from './wide-icon-video.component';

describe('WideIconVideoComponent', () => {
  let component: WideIconVideoComponent;
  let fixture: ComponentFixture<WideIconVideoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ WideIconVideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WideIconVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

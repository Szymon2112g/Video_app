import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { VerticalListVideoComponent } from './vertical-list-video.component';

describe('VerticalListVideoComponent', () => {
  let component: VerticalListVideoComponent;
  let fixture: ComponentFixture<VerticalListVideoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ VerticalListVideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerticalListVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

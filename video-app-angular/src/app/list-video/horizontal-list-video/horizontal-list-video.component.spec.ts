import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { HorizontalListVideoComponent } from './horizontal-list-video.component';

describe('HorizontalListVideoComponent', () => {
  let component: HorizontalListVideoComponent;
  let fixture: ComponentFixture<HorizontalListVideoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ HorizontalListVideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorizontalListVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

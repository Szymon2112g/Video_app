import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ShowvideoComponent } from './showvideo.component';

describe('ShowvideoComponent', () => {
  let component: ShowvideoComponent;
  let fixture: ComponentFixture<ShowvideoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowvideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowvideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

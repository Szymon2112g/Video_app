import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { AddvideoComponent } from './addvideo.component';

describe('AddvideoComponent', () => {
  let component: AddvideoComponent;
  let fixture: ComponentFixture<AddvideoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ AddvideoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddvideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { UserSiteComponent } from './user-site.component';

describe('UserSiteComponent', () => {
  let component: UserSiteComponent;
  let fixture: ComponentFixture<UserSiteComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserSiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

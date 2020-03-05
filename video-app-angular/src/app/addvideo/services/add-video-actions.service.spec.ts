import { TestBed } from '@angular/core/testing';

import { AddVideoActionsService } from './add-video-actions.service';

describe('AddVideoActionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddVideoActionsService = TestBed.get(AddVideoActionsService);
    expect(service).toBeTruthy();
  });
});

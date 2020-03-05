import { TestBed } from '@angular/core/testing';

import { ShowVideoActionsService } from './show-video-actions.service';

describe('ShowVideoActionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShowVideoActionsService = TestBed.get(ShowVideoActionsService);
    expect(service).toBeTruthy();
  });
});

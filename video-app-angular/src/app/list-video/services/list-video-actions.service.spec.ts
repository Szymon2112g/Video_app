import { TestBed } from '@angular/core/testing';

import { ListVideoActionsService } from './list-video-actions.service';

describe('ListVideoActionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListVideoActionsService = TestBed.get(ListVideoActionsService);
    expect(service).toBeTruthy();
  });
});

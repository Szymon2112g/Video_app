import { TestBed } from '@angular/core/testing';

import { VideoappdatabaseService } from './videoappdatabase.service';

describe('VideoappdatabaseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VideoappdatabaseService = TestBed.get(VideoappdatabaseService);
    expect(service).toBeTruthy();
  });
});

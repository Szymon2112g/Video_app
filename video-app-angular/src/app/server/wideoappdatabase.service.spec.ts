import { TestBed } from '@angular/core/testing';

import { WideoappdatabaseService } from './wideoappdatabase.service';

describe('WideoappdatabaseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WideoappdatabaseService = TestBed.get(WideoappdatabaseService);
    expect(service).toBeTruthy();
  });
});

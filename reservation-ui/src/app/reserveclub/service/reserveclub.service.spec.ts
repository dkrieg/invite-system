import { TestBed } from '@angular/core/testing';

import { ReserveclubService } from './reserveclub.service';

describe('ReserveclubService', () => {
  let service: ReserveclubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReserveclubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

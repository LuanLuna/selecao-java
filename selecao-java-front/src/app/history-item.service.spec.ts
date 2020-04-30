import { TestBed } from '@angular/core/testing';

import { HistoryItemService } from './history-item.service';

describe('HistoryItemService', () => {
  let service: HistoryItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistoryItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

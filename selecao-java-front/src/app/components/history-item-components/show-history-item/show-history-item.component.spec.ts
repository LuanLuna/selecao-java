import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowHistoryItemComponent } from './show-history-item.component';

describe('ShowHistoryItemComponent', () => {
  let component: ShowHistoryItemComponent;
  let fixture: ComponentFixture<ShowHistoryItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowHistoryItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowHistoryItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

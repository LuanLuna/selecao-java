import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHistoryItemComponent } from './list-history-item.component';

describe('ListHistoryItemComponent', () => {
  let component: ListHistoryItemComponent;
  let fixture: ComponentFixture<ListHistoryItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListHistoryItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListHistoryItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

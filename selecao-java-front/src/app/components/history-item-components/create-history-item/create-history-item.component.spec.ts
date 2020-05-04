import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHistoryItemComponent } from './create-history-item.component';

describe('CreateHistoryItemComponent', () => {
  let component: CreateHistoryItemComponent;
  let fixture: ComponentFixture<CreateHistoryItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateHistoryItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateHistoryItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

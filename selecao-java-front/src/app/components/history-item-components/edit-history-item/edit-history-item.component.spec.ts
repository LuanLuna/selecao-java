import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHistoryItemComponent } from './edit-history-item.component';

describe('EditHistoryItemComponent', () => {
  let component: EditHistoryItemComponent;
  let fixture: ComponentFixture<EditHistoryItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditHistoryItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditHistoryItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

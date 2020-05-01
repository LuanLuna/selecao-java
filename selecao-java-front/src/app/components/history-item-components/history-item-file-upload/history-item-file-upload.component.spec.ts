import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryItemFileUploadComponent } from './history-item-file-upload.component';

describe('HistoryItemFileUploadComponent', () => {
  let component: HistoryItemFileUploadComponent;
  let fixture: ComponentFixture<HistoryItemFileUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryItemFileUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryItemFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

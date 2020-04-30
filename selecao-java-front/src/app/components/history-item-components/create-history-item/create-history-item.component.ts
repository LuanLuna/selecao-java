import { Component, OnInit } from '@angular/core';
import { HistoryItem } from '../../../Entity/historyItem';
import { HistoryItemService } from '../../../history-item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-history-item',
  templateUrl: './create-history-item.component.html',
  styleUrls: ['./create-history-item.component.scss']
})
export class CreateHistoryItemComponent implements OnInit {
  historyItem: HistoryItem = new HistoryItem();

  constructor(private historyItemService: HistoryItemService, private router: Router) { }

  ngOnInit(): void {
  }

  newUser(): void {
    this.historyItem = new HistoryItem();
  }

  save() {
    this.historyItemService.createHistoryItem(this.historyItem)
      .subscribe(data => console.log(data), error => console.log(error));
    this.historyItem = new HistoryItem();
    this.list();
  }

  onSubmit() {
    this.save();
  }

  list(){
    this.router.navigate(['/history']);
  }

}

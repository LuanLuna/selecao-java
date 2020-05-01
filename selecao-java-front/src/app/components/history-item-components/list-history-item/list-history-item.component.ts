import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {HistoryItem} from '../../../Entity/historyItem';
import {HistoryItemService} from '../../../history-item.service';

@Component({
  selector: 'app-list-history-item',
  templateUrl: './list-history-item.component.html',
  styleUrls: ['./list-history-item.component.scss']
})
export class ListHistoryItemComponent implements OnInit {
  historyItens: Observable<HistoryItem[]>;

  constructor(private historyItemService: HistoryItemService, private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.historyItens = this.historyItemService.getSimpleHistoryItemList();
  }
  deleteHistoryItem(id: number) {
    this.historyItemService.deleteHistoryItem(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  historyItemDetails(id: number){
    this.router.navigate(['history/details', id]);
  }

  newHistoryItem(){
    this.router.navigate(['history/create']);
  }

  upload(){
    this.router.navigate(['history/upload']);
  }
}

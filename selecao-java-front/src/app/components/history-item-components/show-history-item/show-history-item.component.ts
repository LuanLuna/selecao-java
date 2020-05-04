import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HistoryItem} from '../../../Entity/historyItem';
import {HistoryItemService} from '../../../history-item.service';

@Component({
  selector: 'app-show-history-item',
  templateUrl: './show-history-item.component.html',
  styleUrls: ['./show-history-item.component.scss']
})
export class ShowHistoryItemComponent implements OnInit {
  paramLabel: string;
  id: number;
  historyItem: HistoryItem;

  constructor(private historyItemService: HistoryItemService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.historyItem = new HistoryItem();
    this.paramLabel = 'id';

    this.id = this.route.snapshot.params[this.paramLabel];

    this.historyItemService.getHistoryItem(this.id)
      .subscribe(data => {
        console.log(data);
        this.historyItem = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['/history']);
  }

  edit(id: number){
    this.router.navigate(['history/update', id]);
  }

}

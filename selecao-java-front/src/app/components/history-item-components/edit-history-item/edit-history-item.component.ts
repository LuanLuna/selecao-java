import { Component, OnInit } from '@angular/core';
import { HistoryItem } from '../../../Entity/historyItem';
import { HistoryItemService } from '../../../history-item.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-history-item',
  templateUrl: './edit-history-item.component.html',
  styleUrls: ['./edit-history-item.component.scss']
})
export class EditHistoryItemComponent implements OnInit {
  id: number;
  paramLabel: string;
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

  updateUser() {
    this.historyItemService.updateHistoryItem(this.id, this.historyItem)
      .subscribe(data => console.log(data), error => console.log(error));
    this.historyItem = new HistoryItem();
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

  goToDetails(id: number) {
    this.router.navigate(['history/details', id]);
  }

}

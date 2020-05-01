import { Component, OnInit } from '@angular/core';
import { HistoryItem } from '../../../Entity/historyItem';
import { HistoryItemService } from '../../../history-item.service';
import { Router } from '@angular/router';
import {Merchant} from '../../../Entity/merchant';
import {Product} from '../../../Entity/product';
import {State} from '../../../Entity/State';
import {City} from '../../../Entity/city';
import {Label} from '../../../Entity/label';

@Component({
  selector: 'app-create-history-item',
  templateUrl: './create-history-item.component.html',
  styleUrls: ['./create-history-item.component.scss']
})
export class CreateHistoryItemComponent implements OnInit {
  historyItem: HistoryItem = new HistoryItem();
  state: State = new State();
  product: Product = new Product();
  merchant: Merchant = new Merchant();
  city: City = new City();
  label: Label = new Label();

  constructor(private historyItemService: HistoryItemService, private router: Router) { }

  ngOnInit(): void {
    this.city.state = this.state;
    this.historyItem.city = this.city;
    this.historyItem.product = this.product;
    this.historyItem.merchant = this.merchant;
    this.historyItem.label = this.label;
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

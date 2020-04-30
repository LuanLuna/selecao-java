import {City} from './city';
import {Merchant} from './merchant';
import {Label} from './label';
import {Product} from './product';

export class HistoryItem {
  id: number;
  city: City;
  merchant: Merchant;
  product: Product;
  label: Label;
  date: string;
  purchasePrice: number;
  salesPrice: string;
}

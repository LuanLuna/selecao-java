import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FileUploader} from 'ng2-file-upload';

@Injectable({
  providedIn: 'root'
})
export class HistoryItemService {
  private baseUrl = 'http://localhost:8080/pricehistory';

  constructor(private http: HttpClient) { }

  getHistoryItem(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createHistoryItem(historyItem: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, historyItem);
  }

  updateHistoryItem(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteHistoryItem(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getSimpleHistoryItemList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getHistoryItemListGroupedByDate(): Observable<any> {
    return this.http.get(`${this.baseUrl}/grouped/date`);
  }

  getHistoryItemListGroupedByMerchant(): Observable<any> {
    return this.http.get(`${this.baseUrl}/grouped/merchant`);
  }
}

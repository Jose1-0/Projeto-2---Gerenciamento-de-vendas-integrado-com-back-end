import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UC06Service {
  private baseUrl = `${environment.backendUrl}/uc06`;

  constructor(private http: HttpClient) {}

  addItemToCart(item: any) {
    return this.http.post(`${this.baseUrl}/addItem`, item);
  }

  finalizeSale(sale: any) {
    return this.http.post(`${this.baseUrl}/finalize`, sale);
  }
}

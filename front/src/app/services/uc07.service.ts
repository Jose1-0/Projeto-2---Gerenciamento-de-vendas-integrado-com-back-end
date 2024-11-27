import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UC07Service {
  private baseUrl = `${environment.backendUrl}/uc07`;

  constructor(private http: HttpClient) {}

  getSalesData() {
    return this.http.get(`${this.baseUrl}/salesData`);
  }
}

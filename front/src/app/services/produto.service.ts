import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private apiUrl = 'http://localhost:8081/listar'; // URL base do backend

  constructor(private http: HttpClient) {}

  listarProdutos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}

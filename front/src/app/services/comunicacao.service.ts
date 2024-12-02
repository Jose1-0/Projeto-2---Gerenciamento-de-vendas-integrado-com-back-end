import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComunicacaoService {
  private uc06Url = 'http://localhost:8081/api/vendas';
  private uc07Url = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {}

  // Métodos para UC06
  obterQuantidadesMensais(): Observable<any> {
    return this.http.get(`${this.uc06Url}/quantidades-mensais`);
  }

  realizarVenda(venda: any): Observable<any> {
    return this.http.post(`${this.uc06Url}/realizar-venda`, venda);
  }

  // Métodos para UC07
  gerarGraficoLinhas(): Observable<Blob> {
    return this.http.get(`${this.uc07Url}/relatorios-e-graficos/grafico-quantidades-compradas-vendidas-linhas`, {
      responseType: 'blob'
    });
  }

  gerarGraficoBarras(): Observable<Blob> {
    return this.http.get(`${this.uc07Url}/relatorios-e-graficos/grafico-quantidades-compradas-vendidas-barras`, {
      responseType: 'blob'
    });
  }
}

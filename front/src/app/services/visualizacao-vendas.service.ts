import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VisualizacaoVendasService {
  private baseUrl = 'http://localhost:8082/api/relatorios-e-graficos';

  constructor(private http: HttpClient) {}

  obterVendasMensais(): Observable<any> {
    return this.http.get(`${this.baseUrl}/vendas-mensais`).pipe(
      catchError((error) => {
        console.error('Erro ao obter vendas mensais:', error);
        return throwError(() => new Error('Falha ao obter vendas mensais.'));
      })
    );
  }

  obterDadosGraficoLinhas(): Observable<any> {
    return this.http.get(`${this.baseUrl}/grafico-quantidades-compradas-vendidas-linhas`).pipe(
      catchError((error) => {
        console.error('Erro ao obter dados do gráfico de linhas:', error);
        return throwError(() => new Error('Falha ao obter dados do gráfico de linhas.'));
      })
    );
  }

  obterDadosGraficoBarras(): Observable<any> {
    return this.http.get(`${this.baseUrl}/grafico-quantidades-compradas-vendidas-barras`).pipe(
      catchError((error) => {
        console.error('Erro ao obter dados do gráfico de barras:', error);
        return throwError(() => new Error('Falha ao obter dados do gráfico de barras.'));
      })
    );
  }
}

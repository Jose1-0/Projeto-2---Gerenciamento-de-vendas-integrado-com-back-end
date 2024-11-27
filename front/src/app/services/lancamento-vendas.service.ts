import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LancamentoVendasService {
  private baseUrl = 'http://localhost:8080/api/vendas'; // Base URL do backend
  private produtosUrl = 'http://localhost:8080/api/produtos'; // Endpoint para listar produtos

  constructor(private http: HttpClient) {}

  // Lista todos os produtos disponíveis
  listarProdutos(): Observable<any[]> {
    return this.http.get<any[]>(this.produtosUrl);
  }

  // Verifica os detalhes de um produto
  verificarProduto(produtoId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/verificar-produto/${produtoId}`);
  }

  // Verifica o estoque de um produto
  verificarEstoque(produtoId: number, quantidadeVendida: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/verificar-estoque/${produtoId}/${quantidadeVendida}`);
  }

  // Calcula o valor da venda para um produto e quantidade específica
  calcularValorVenda(produtoId: number, quantidadeVendida: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/calcular-valor-venda/${produtoId}/${quantidadeVendida}`);
  }

  // Registra uma venda no backend
  realizarVenda(venda: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/realizar-venda`, venda);
  }
}

import { Component, OnInit } from '@angular/core';
import { LancamentoVendasService } from '../../services/lancamento-vendas.service';

@Component({
  selector: 'app-lancamento-vendas',
  templateUrl: './lancamento-vendas.component.html',
  styleUrls: ['./lancamento-vendas.component.scss'],
})
export class LancamentoVendasComponent implements OnInit {
  produtos: any[] = []; // Lista de produtos disponíveis
  carrinho: any[] = []; // Itens no carrinho
  total: number = 0; // Total da venda

  constructor(private vendasService: LancamentoVendasService) {}

  ngOnInit() {
    this.carregarProdutos();
  }

  // Carrega a lista de produtos do backend
  carregarProdutos() {
    this.vendasService.listarProdutos().subscribe((response: any[]) => {
      this.produtos = response;
    });
  }

  // Adiciona um produto ao carrinho
  adicionarAoCarrinho(produto: any) {
    const itemExistente = this.carrinho.find(
      (item) => item.id === produto.id
    );
    if (itemExistente) {
      itemExistente.quantidade++;
      itemExistente.subtotal += produto.preco;
    } else {
      this.carrinho.push({
        id: produto.id,
        nome: produto.nome,
        quantidade: 1,
        subtotal: produto.preco,
      });
    }
    this.calcularTotal();
  }

  // Calcula o total da venda
  calcularTotal() {
    this.total = this.carrinho.reduce(
      (acc, item) => acc + item.subtotal,
      0
    );
  }

  // Finaliza a venda e envia para o backend
  finalizarVenda() {
    const venda = {
      itens: this.carrinho.map((item) => ({
        produtoId: item.id,
        quantidade: item.quantidade,
      })),
    };
    this.vendasService.realizarVenda(venda).subscribe(() => {
      alert('Venda realizada com sucesso!');
      this.carrinho = [];
      this.total = 0;
    });
  }
}

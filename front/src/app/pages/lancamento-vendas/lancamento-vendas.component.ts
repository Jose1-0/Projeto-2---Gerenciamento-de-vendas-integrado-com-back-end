import { Component, OnInit } from '@angular/core';
import { LancamentoVendasService } from '../../services/lancamento-vendas.service';
import { ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-lancamento-vendas',
  templateUrl: './lancamento-vendas.component.html',
  styleUrls: ['./lancamento-vendas.component.scss'],
})
export class LancamentoVendasComponent implements OnInit {
  produtos: any[] = []; // Lista de produtos disponíveis
  carrinho: any[] = []; // Itens no carrinho
  vendas: any[] = []; // Lista de vendas realizadas
  total: number = 0; // Total da venda

  constructor(
    private vendasService: LancamentoVendasService,
    private produtoService: ProdutoService
  ) {}

  ngOnInit(): void {
    this.carregarProdutos(); // Inicializa a lista de produtos
    this.carregarVendas(); // Inicializa a lista de vendas
  }

  /**
   * Carrega a lista de produtos do backend
   */
  carregarProdutos(): void {
    this.produtoService.listarProdutos().subscribe(
      (response: any[]) => {
        this.produtos = response;
      },
      (error) => {
        console.error('Erro ao carregar produtos:', error);
      }
    );
  }

  /**
   * Carrega a lista de vendas realizadas do backend
   */
  carregarVendas(): void {
    this.vendasService.listarVendas().subscribe(
      (response: any[]) => {
        this.vendas = response;
      },
      (error) => {
        console.error('Erro ao carregar vendas:', error);
      }
    );
  }

  /**
   * Adiciona um produto ao carrinho
   */
  adicionarAoCarrinho(produto: any): void {
    const itemExistente = this.carrinho.find((item) => item.id === produto.id);
    if (itemExistente) {
      itemExistente.quantidade++;
      itemExistente.subtotal += produto.preco;
    } else {
      this.carrinho.push({
        id: produto.id,
        nome: produto.nome,
        preco: produto.preco,
        quantidade: 1,
        subtotal: produto.preco,
      });
    }
    this.calcularTotal();
  }

  /**
   * Remove um produto do carrinho
   */
  removerDoCarrinho(produtoId: number): void {
    const index = this.carrinho.findIndex((item) => item.id === produtoId);
    if (index !== -1) {
      this.carrinho.splice(index, 1);
      this.calcularTotal();
    }
  }

  /**
   * Calcula o total da venda
   */
  calcularTotal(): void {
    this.total = this.carrinho.reduce((acc, item) => acc + item.subtotal, 0);
  }

  /**
   * Finaliza a venda e limpa o carrinho
   */
  finalizarVenda(): void {
    if (this.carrinho.length === 0) {
      alert('Adicione produtos ao carrinho antes de finalizar a venda.');
      return;
    }

    const venda = {
      itens: this.carrinho.map((item) => ({
        produtoId: item.id,
        quantidade: item.quantidade,
      })),
    };

    this.vendasService.realizarVenda(venda).subscribe(
      () => {
        alert('Venda realizada com sucesso!');
        this.carrinho = [];
        this.total = 0;
        this.carregarVendas(); // Atualiza a lista de vendas
      },
      (error) => {
        console.error('Erro ao realizar venda:', error);
        alert('Ocorreu um erro ao realizar a venda.');
      }
    );
  }
}

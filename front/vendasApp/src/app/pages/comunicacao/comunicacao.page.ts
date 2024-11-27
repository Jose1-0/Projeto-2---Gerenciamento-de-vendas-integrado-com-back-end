import { Component } from '@angular/core';
import { ComunicacaoService } from 'src/app/services/comunicacao.service';

@Component({
  selector: 'app-comunicacao',
  templateUrl: './comunicacao.page.html',
  styleUrls: ['./comunicacao.page.scss'],
})
export class ComunicacaoPage {
  venda = { produtoId: 0, quantidadeVendida: 0 };
  grafico: string | null = null;

  constructor(private comunicacaoService: ComunicacaoService) {}

  realizarVenda() {
    this.comunicacaoService.realizarVenda(this.venda).subscribe(
      response => {
        alert('Venda realizada com sucesso!');
      },
      error => {
        console.error('Erro ao realizar venda:', error);
        alert('Erro ao realizar venda!');
      }
    );
  }

  gerarGraficoLinhas() {
    this.comunicacaoService.gerarGraficoLinhas().subscribe(
      response => {
        this.grafico = URL.createObjectURL(response);
      },
      error => {
        console.error('Erro ao gerar gráfico de linhas:', error);
        alert('Erro ao gerar gráfico!');
      }
    );
  }

  gerarGraficoBarras() {
    this.comunicacaoService.gerarGraficoBarras().subscribe(
      response => {
        this.grafico = URL.createObjectURL(response);
      },
      error => {
        console.error('Erro ao gerar gráfico de barras:', error);
        alert('Erro ao gerar gráfico!');
      }
    );
  }
}

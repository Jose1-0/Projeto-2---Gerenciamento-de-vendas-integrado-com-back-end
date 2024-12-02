import { Component, OnInit } from '@angular/core';
import { VisualizacaoVendasService } from '../../services/visualizacao-vendas.service';
import { Chart } from 'chart.js'; // Importação do Chart.js

@Component({
  selector: 'app-visualizacao-vendas',
  templateUrl: './visualizacao-vendas.component.html',
  styleUrls: ['./visualizacao-vendas.component.scss'],
})
export class VisualizacaoVendasComponent implements OnInit {
  vendas: any[] = []; // Lista de vendas
  graficoLinhas: any; // Dados para o gráfico de linhas
  graficoBarras: any; // Dados para o gráfico de barras

  constructor(private visualizacaoService: VisualizacaoVendasService) {}

  ngOnInit() {
    this.carregarVendas();
    this.carregarDadosGraficos();
  }

  // Carrega a lista de vendas mensais
  carregarVendas() {
    this.visualizacaoService.obterVendasMensais().subscribe((response: any[]) => {
      this.vendas = response;
    });
  }

  // Carrega os dados para os gráficos
  carregarDadosGraficos() {
    this.visualizacaoService.obterDadosGraficoLinhas().subscribe((response: any) => {
      this.graficoLinhas = response;
      this.renderizarGraficoLinhas();
    });

    this.visualizacaoService.obterDadosGraficoBarras().subscribe((response: any) => {
      this.graficoBarras = response;
      this.renderizarGraficoBarras();
    });
  }

  // Renderiza o gráfico de linhas
  renderizarGraficoLinhas() {
    const canvas = document.getElementById('graficoLinhas') as HTMLCanvasElement;
    const ctx = canvas?.getContext('2d');

    if (ctx) {
      new Chart(ctx, {
        type: 'line',
        data: this.graficoLinhas,
        options: {
          responsive: true,
        },
      });
    } else {
      console.error('Canvas para gráfico de linhas não encontrado.');
    }
  }

  // Renderiza o gráfico de barras
  renderizarGraficoBarras() {
    const canvas = document.getElementById('graficoBarras') as HTMLCanvasElement;
    const ctx = canvas?.getContext('2d');

    if (ctx) {
      new Chart(ctx, {
        type: 'bar',
        data: this.graficoBarras,
        options: {
          responsive: true,
        },
      });
    } else {
      console.error('Canvas para gráfico de barras não encontrado.');
    }
  }
}

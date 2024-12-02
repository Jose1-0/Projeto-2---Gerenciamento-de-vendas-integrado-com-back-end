import { Component } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ComunicacaoService } from '../services/comunicacao.service'


@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  graficoUrl: string | ArrayBuffer | null = null;  // URL ou imagem do gráfico

  constructor(private graficoService: GraficoService) { }

  ngOnInit() {
    this.carregarGrafico();
  }

  carregarGrafico() {
    this.graficoService.getGrafico().subscribe((response: Blob) => {
      // Converte a resposta Blob para uma URL utilizável em um <img>
      const reader = new FileReader();
      reader.onloadend = () => {
        this.graficoUrl = reader.result;
      };
      reader.readAsDataURL(response);
    }, (error) => {
      console.error('Erro ao carregar o gráfico', error);
    });
  }
}

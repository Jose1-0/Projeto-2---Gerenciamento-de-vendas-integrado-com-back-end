import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { LancamentoVendasComponent } from './pages/lancamento-vendas/lancamento-vendas.component';
import { VisualizacaoVendasComponent } from './pages/lancamento-vendas/visualizacao-vendas.component';

@NgModule({
  declarations: [
    AppComponent, // Componente principal
    LancamentoVendasComponent, // Componente de Lançamento de Vendas
    VisualizacaoVendasComponent // Componente de Visualização de Vendas
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }],
  bootstrap: [AppComponent],
})
export class AppModule {}

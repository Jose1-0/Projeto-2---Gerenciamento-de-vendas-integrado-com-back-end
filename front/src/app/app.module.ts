import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MenuComponent } from './menu/menu.component';
import { VisualizacaoVendasComponent } from './pages/visualizacao-vendas/visualizacao-vendas.component';
import { LancamentoVendasComponent } from './pages/lancamento-vendas/lancamento-vendas.component'; // Certifique-se de importar este componente.

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    VisualizacaoVendasComponent,
    LancamentoVendasComponent
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA], // Permite o uso de web components (Ionic).
  bootstrap: [AppComponent],
})
export class AppModule {}

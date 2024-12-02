import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { LancamentoVendasComponent } from './pages/lancamento-vendas/lancamento-vendas.component';
import { VisualizacaoVendasComponent } from './pages/visualizacao-vendas/visualizacao-vendas.component';

const routes: Routes = [
  // Redireciona a rota inicial para 'comunicacao'
  {
    path: '',
    redirectTo: 'comunicacao',
    pathMatch: 'full',
  },
  // Lazy loading para o módulo de comunicação
  {
    path: 'comunicacao',
    loadChildren: () =>
      import('./pages/comunicacao/comunicacao.module').then(
        (m) => m.ComunicacaoPageModule
      ),
  },
  // Rota para o componente de lançamento de vendas
  {
    path: 'lancamento-vendas',
    component: LancamentoVendasComponent,
  },
  // Rota para o componente de visualização de vendas
  {
    path: 'visualizacao-vendas',
    component: VisualizacaoVendasComponent,
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules,
    }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}

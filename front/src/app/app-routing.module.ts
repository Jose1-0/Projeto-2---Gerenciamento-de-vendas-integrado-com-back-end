import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { LancamentoVendasComponent } from './pages/lancamento-vendas/lancamento-vendas.component';
import { VisualizacaoVendasComponent } from './pages/lancamento-vendas/visualizacao-vendas.component';

const routes: Routes = [
  { path: '', redirectTo: 'lancamento-vendas', pathMatch: 'full' },
  { path: 'lancamento-vendas', component: LancamentoVendasComponent },
  { path: 'visualizacao-vendas', component: VisualizacaoVendasComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}

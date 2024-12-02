import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'comunicacao',
    pathMatch: 'full'
  },
  {
    path: 'comunicacao',
    loadChildren: () => import('./pages/comunicacao/comunicacao.module').then(m => m.ComunicacaoPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule]
})
export class AppRoutingModule {}

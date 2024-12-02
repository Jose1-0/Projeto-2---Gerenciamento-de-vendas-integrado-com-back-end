import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ComunicacaoPage } from './comunicacao.page';

const routes: Routes = [
  {
    path: '',
    component: ComunicacaoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ComunicacaoPageRoutingModule {}

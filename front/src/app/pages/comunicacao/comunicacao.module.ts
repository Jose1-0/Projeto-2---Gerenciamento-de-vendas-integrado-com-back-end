import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ComunicacaoPageRoutingModule } from './comunicacao-routing.module';

import { ComunicacaoPage } from './comunicacao.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ComunicacaoPageRoutingModule
  ],
  declarations: [ComunicacaoPage]
})
export class ComunicacaoPageModule {}

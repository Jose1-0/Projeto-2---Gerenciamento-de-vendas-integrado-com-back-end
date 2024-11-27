import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ComunicacaoPage } from './comunicacao.page';

describe('ComunicacaoPage', () => {
  let component: ComunicacaoPage;
  let fixture: ComponentFixture<ComunicacaoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ComunicacaoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

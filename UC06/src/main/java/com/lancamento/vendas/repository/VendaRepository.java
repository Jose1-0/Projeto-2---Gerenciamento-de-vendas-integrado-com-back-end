package com.lancamento.vendas.repository;

import com.lancamento.vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    // Método para buscar vendas dentro de um intervalo de datas
    List<Venda> findByDataHoraVendaBetween(Date dataInicial, Date dataFinal);
}

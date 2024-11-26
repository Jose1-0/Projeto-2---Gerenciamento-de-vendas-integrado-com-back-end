package com.lancamento.vendas.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lancamento.vendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    @Query("SELECT new map(FUNCTION('DATE_FORMAT', v.dataHoraVenda, '%Y-%m') as mes, SUM(v.valorTotalVenda) as valorVenda) " +
            "FROM Venda v GROUP BY FUNCTION('DATE_FORMAT', v.dataHoraVenda, '%Y-%m')")
     List<Map<String, Object>> findVendasMensais();

}

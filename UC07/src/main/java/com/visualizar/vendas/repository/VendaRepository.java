package com.visualizar.vendas.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visualizar.vendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    @Query("SELECT new map(FUNCTION('DATE_FORMAT', v.dataHoraVenda, '%Y-%m') as mes, SUM(v.valorTotalVenda) as valorVenda) " +
            "FROM Venda v GROUP BY FUNCTION('DATE_FORMAT', v.dataHoraVenda, '%Y-%m')")
     List<Map<String, Object>> findVendasMensais();
    
    @Query("SELECT EXTRACT(MONTH FROM v.dataHoraVenda) AS mes, " +
            "SUM(v.valorTotalVenda) AS totalVenda " +
            "FROM Venda v WHERE EXTRACT(YEAR FROM v.dataHoraVenda) = :ano " +
            "GROUP BY EXTRACT(MONTH FROM v.dataHoraVenda)")
     List<Object[]> findTotalVendaPorMes(int ano);

     // Consulta para o gráfico de barras (quantidade vendida por mês)
     @Query("SELECT EXTRACT(MONTH FROM v.dataHoraVenda) AS mes, " +
            "SUM(v.quantidadeVendida) AS totalVendida " +
            "FROM Venda v WHERE EXTRACT(YEAR FROM v.dataHoraVenda) = :ano " +
            "GROUP BY EXTRACT(MONTH FROM v.dataHoraVenda)")
     List<Object[]> findQuantidadeVendidaPorMes(int ano);
}

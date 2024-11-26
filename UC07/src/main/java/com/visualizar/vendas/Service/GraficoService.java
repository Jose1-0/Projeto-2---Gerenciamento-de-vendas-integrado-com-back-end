package com.visualizar.vendas.Service;

import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

@Service
public class GraficoService {

    // Método para gerar gráfico de linha (já fornecido)
    public ChartPanel gerarGraficoVendas(List<Map<String, Object>> vendasMensais) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> venda : vendasMensais) {
            String mes = venda.get("mes").toString();
            Double valorVenda = Double.parseDouble(venda.get("valorVenda").toString());
            dataset.addValue(valorVenda, "Vendas", mes);
        }

        JFreeChart chart = ChartFactory.createLineChart(
            "Vendas Mensais",  // Título
            "Mês",  // Eixo X
            "Valor de Venda",  // Eixo Y
            dataset  // Dados
        );

        return new ChartPanel(chart);
    }

    // Método para gerar gráfico de barras (quantidade vendida vs. quantidade comprada)
    public ChartPanel gerarGraficoBarras(List<Map<String, Object>> dadosMensais) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> dados : dadosMensais) {
            String mes = dados.get("mes").toString();
            Double quantidadeComprada = Double.parseDouble(dados.get("quantidadeComprada").toString());
            Double quantidadeVendida = Double.parseDouble(dados.get("quantidadeVendida").toString());

            // Adicionando valores para a quantidade comprada
            dataset.addValue(quantidadeComprada, "Quantidade Comprada", mes);

            // Adicionando valores para a quantidade vendida
            dataset.addValue(quantidadeVendida, "Quantidade Vendida", mes);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Quantidades Compradas vs. Vendidas",  // Título
            "Mês",  // Eixo X
            "Quantidade",  // Eixo Y
            dataset,  // Dados
            org.jfree.chart.plot.PlotOrientation.VERTICAL,  // Tipo de gráfico
            true,  // Legenda
            true,  // Tooltips
            false  // URLs
        );

        return new ChartPanel(chart);
    }

    // Método para gerar gráfico de barras (valor total de custo vs. valor total de venda)
    public ChartPanel gerarGraficoComparativoCustoVenda(List<Map<String, Object>> dadosMensais) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> dados : dadosMensais) {
            String mes = dados.get("mes").toString();
            Double valorCusto = Double.parseDouble(dados.get("valorCusto").toString());
            Double valorVenda = Double.parseDouble(dados.get("valorVenda").toString());

            // Adicionando valores para o valor total de custo
            dataset.addValue(valorCusto, "Custo Total", mes);

            // Adicionando valores para o valor total de venda
            dataset.addValue(valorVenda, "Venda Total", mes);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Custo vs. Venda Mensal",  // Título
            "Mês",  // Eixo X
            "Valor (R$)",  // Eixo Y
            dataset,  // Dados
            org.jfree.chart.plot.PlotOrientation.VERTICAL,  // Tipo de gráfico
            true,  // Legenda
            true,  // Tooltips
            false  // URLs
        );

        return new ChartPanel(chart);
    }

    // Outros métodos gráficos podem ser adicionados aqui conforme necessário
}

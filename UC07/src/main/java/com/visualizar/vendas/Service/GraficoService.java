package com.visualizar.vendas.Service;

import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancamento.vendas.repository.VendaRepository;


@Service
public class GraficoService {

    // Método para gerar gráfico de linha (já fornecido)
	@Autowired
    private VendaRepository vendaRepository;  // Injeta o repositório de vendas

    // Método para gerar gráfico de vendas mensais
    public ChartPanel gerarGraficoVendas() {
        // Obtém os dados de vendas mensais diretamente do banco
        List<Map<String, Object>> vendasMensais = vendaRepository.findVendasMensais();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map<String, Object> venda : vendasMensais) {
            String mes = venda.get("mes").toString();  // Obtém o mês
            Double valorVenda = Double.parseDouble(venda.get("valorVenda").toString());  // Obtém o valor de venda

            // Adiciona os dados ao dataset
            dataset.addValue(valorVenda, "Vendas", mes);
        }

        // Cria o gráfico de linha
        JFreeChart chart = ChartFactory.createLineChart(
                "Vendas Mensais",  // Título
                "Mês",  // Eixo X
                "Valor de Venda",  // Eixo Y
                dataset  // Dados
        );

        return new ChartPanel(chart);
    }

    // Método para gerar gráfico de barras (quantidade comprada vs. quantidade vendida)
    public ChartPanel gerarGraficoBarras(List<Map<String, Object>> dadosMensais) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> dados : dadosMensais) {
            String mes = dados.get("mes").toString();
            Double quantidadeComprada = Double.parseDouble(dados.get("quantidadeComprada").toString());
            Double quantidadeVendida = Double.parseDouble(dados.get("quantidadeVendida").toString());

            // Adiciona valores para a quantidade comprada
            dataset.addValue(quantidadeComprada, "Quantidade Comprada", mes);

            // Adiciona valores para a quantidade vendida
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

    // Método para gerar gráfico comparativo de custo vs. venda
    public ChartPanel gerarGraficoComparativoCustoVenda(List<Map<String, Object>> dadosMensais) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> dados : dadosMensais) {
            String mes = dados.get("mes").toString();
            Double valorCusto = Double.parseDouble(dados.get("valorCusto").toString());
            Double valorVenda = Double.parseDouble(dados.get("valorVenda").toString());

            // Adiciona valores para o valor total de custo
            dataset.addValue(valorCusto, "Custo Total", mes);

            // Adiciona valores para o valor total de venda
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

package com.visualizar.vendas.Service;

import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visualizar.vendas.repository.VendaRepository;

@Service
public class GraficoService {

	// Método para gerar gráfico de linha (já fornecido)
	@Autowired
	private VendaRepository vendaRepository; // Injeta o repositório de vendas

	// Método para gerar gráfico de vendas mensais
	public ChartPanel gerarGraficoVendas() {
		// Obtém os dados de vendas mensais diretamente do banco
		List<Map<String, Object>> vendasMensais = vendaRepository.findVendasMensais();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Map<String, Object> venda : vendasMensais) {
			String mes = venda.get("mes").toString(); // Obtém o mês
			Double valorVenda = Double.parseDouble(venda.get("valorVenda").toString()); // Obtém o valor de venda

			// Adiciona os dados ao dataset
			dataset.addValue(valorVenda, "Vendas", mes);
		}

		// Cria o gráfico de linha
		JFreeChart chart = ChartFactory.createLineChart("Vendas Mensais", // Título
				"Mês", // Eixo X
				"Valor de Venda", // Eixo Y
				dataset // Dados
		);

		return new ChartPanel(chart);
	}



	public ChartPanel gerarGraficoLinhas(List<Map<String, Object>> dadosMensais) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Iterando sobre os dados mockados para gerar o gráfico
		for (Map<String, Object> dados : dadosMensais) {
			String mes = dados.get("mes").toString(); // Obtendo o mês
			Double quantidadeComprada = Double.parseDouble(dados.get("quantidadeComprada").toString()); // Quantidade
																										// comprada
			Double quantidadeVendida = Double.parseDouble(dados.get("quantidadeVendida").toString()); // Quantidade
																										// vendida

			// Adicionando valores ao dataset para o gráfico de linha
			dataset.addValue(quantidadeComprada, "Quantidade Comprada", mes);
			dataset.addValue(quantidadeVendida, "Quantidade Vendida", mes);
		}

		// Criando o gráfico de linhas
		JFreeChart chart = ChartFactory.createLineChart("Quantidades Compradas vs. Vendidas", // Título do gráfico
				"Mês", // Eixo X
				"Quantidade", // Eixo Y
				dataset, // Dados para o gráfico
				org.jfree.chart.plot.PlotOrientation.VERTICAL, // Tipo de gráfico (vertical)
				true, // Exibe a legenda
				true, // Exibe tooltips
				false // Não exibe URLs
		);

		// Retorna o painel com o gráfico
		return new ChartPanel(chart);
	}
	
	public ChartPanel gerarGraficoBarras(List<Map<String, Object>> dadosMensais) {
	    // Criando um dataset para o gráfico de barras
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    // Iterando sobre os dados simulados (mockados)
	    for (Map<String, Object> dados : dadosMensais) {
	        String mes = dados.get("mes").toString();  // Obtendo o mês
	        Double quantidadeComprada = Double.parseDouble(dados.get("quantidadeComprada").toString());  // Quantidade comprada
	        Double quantidadeVendida = Double.parseDouble(dados.get("quantidadeVendida").toString());  // Quantidade vendida

	        // Adicionando valores ao dataset para o gráfico de barras
	        dataset.addValue(quantidadeComprada, "Quantidade Comprada", mes);
	        dataset.addValue(quantidadeVendida, "Quantidade Vendida", mes);
	    }

	    // Criando o gráfico de barras
	    JFreeChart chart = ChartFactory.createBarChart(
	            "Quantidades Compradas vs. Vendidas",  // Título do gráfico
	            "Mês",  // Eixo X
	            "Quantidade",  // Eixo Y
	            dataset,  // Dados para o gráfico
	            org.jfree.chart.plot.PlotOrientation.VERTICAL,  // Tipo de gráfico (vertical)
	            true,  // Exibe a legenda
	            true,  // Exibe tooltips
	            false  // Não exibe URLs
	    );

	    // Retorna o painel com o gráfico
	    return new ChartPanel(chart);
	}

}

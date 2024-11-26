package com.visualizar.vendas.controllar;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visualizar.vendas.Service.GraficoService;
import com.visualizar.vendas.Service.RelatorioService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/relatorios-e-graficos")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private GraficoService graficoService;

	// Endpoint para obter as vendas mensais (dados para relatório)
	@GetMapping("/vendas-mensais")
	public ResponseEntity<List<Map<String, Object>>> vendasMensais() {
		try {
			List<Map<String, Object>> vendasMensais = relatorioService.getVendasMensais();
			return ResponseEntity.ok(vendasMensais);
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(List.of(Map.of("error", "Erro ao obter vendas mensais", "message", e.getMessage())));
		}
	}

	// Endpoint para obter as quantidades mensais (dados para relatório)
	@GetMapping("/quantidades-mensais")
	public ResponseEntity<List<Map<String, Object>>> quantidadesMensais() {
		try {
			List<Map<String, Object>> quantidadesMensais = relatorioService.getQuantidadesMensais();
			return ResponseEntity.ok(quantidadesMensais);
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(List.of(Map.of("error", "Erro ao obter quantidades mensais", "message", e.getMessage())));
		}
	}


	@GetMapping("/grafico-quantidades-compradas-vendidas-linhas")
	public void gerarGraficoQuantidadesLinhas(HttpServletResponse response) throws IOException {
		try {
			// Obtém os dados mockados de quantidades (compra e venda)
			List<Map<String, Object>> dadosMensais = relatorioService.getQuantidadesMensais();

			// Verifica se os dados foram obtidos corretamente
			if (dadosMensais == null || dadosMensais.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nenhum dado encontrado para gerar o gráfico.");
				return;
			}

			// Chama o serviço para gerar o gráfico de linhas com os dados
			ChartPanel grafico = graficoService.gerarGraficoLinhas(dadosMensais);

			// Cria o gráfico em imagem

			JFreeChart chart = grafico.getChart();
			BufferedImage image = chart.createBufferedImage(800, 600);

			// Envia a imagem como resposta
			response.setContentType("image/png");
			javax.imageio.ImageIO.write(image, "PNG", response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace(); // Log do erro
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Erro ao gerar o gráfico de quantidades compradas vs. vendidas.");
		}
	}
	
	@GetMapping("/grafico-quantidades-compradas-vendidas-barras")
	public void gerarGraficoQuantidadesBarras(HttpServletResponse response) throws IOException {
	    try {
	        // Obtém os dados simulados de quantidades (compra e venda)
	        List<Map<String, Object>> dadosMensais = relatorioService.getQuantidadesMensais();  // Usando dados mockados

	        // Verifica se os dados foram obtidos corretamente
	        if (dadosMensais == null || dadosMensais.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nenhum dado para gerar o gráfico.");
	            return;
	        }

	        // Chama o serviço para gerar o gráfico de barras com os dados
	        ChartPanel grafico = graficoService.gerarGraficoBarras(dadosMensais);

	        // Cria o gráfico em imagem
	        JFreeChart chart = grafico.getChart();
	        BufferedImage image = chart.createBufferedImage(800, 600);

	        // Envia a imagem como resposta
	        response.setContentType("image/png");
	        javax.imageio.ImageIO.write(image, "PNG", response.getOutputStream());

	    } catch (Exception e) {
	        e.printStackTrace();  // Log do erro
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o gráfico de quantidades compradas vs. vendidas.");
	    }
	}

}

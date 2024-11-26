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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/grafico-vendas-mensais")
    public void gerarGraficoVendasMensais(HttpServletResponse response) throws IOException {
        try {
            // Chama o serviço para gerar o gráfico de vendas mensais
            ChartPanel grafico = graficoService.gerarGraficoVendas();
            JFreeChart chart = grafico.getChart();

            // Cria a imagem do gráfico
            BufferedImage image = chart.createBufferedImage(800, 600);

            // Configura o response para enviar o gráfico em formato PNG
            response.setContentType("image/png");
            javax.imageio.ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o gráfico de vendas mensais.");
        }
    }

    // Endpoint para gerar gráfico de barras (quantidade comprada vs. vendida)
    @GetMapping("/grafico-quantidades-compradas-vendidas")
    public void gerarGraficoQuantidades(HttpServletResponse response) throws IOException {
        try {
            // Chama o serviço para gerar o gráfico de barras (quantidade comprada vs. vendida)
            ChartPanel grafico = graficoService.gerarGraficoBarras(null);  // Passar dados adequados se necessário
            JFreeChart chart = grafico.getChart();

            // Cria a imagem do gráfico
            BufferedImage image = chart.createBufferedImage(800, 600);

            // Configura o response para enviar o gráfico em formato PNG
            response.setContentType("image/png");
            javax.imageio.ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o gráfico de quantidades compradas vs. vendidas.");
        }
    }

    // Endpoint para gerar gráfico comparativo de custo vs. venda
    @GetMapping("/grafico-custo-venda")
    public void gerarGraficoCustoVenda(HttpServletResponse response) throws IOException {
        try {
            // Chama o serviço para gerar o gráfico comparativo de custo vs. venda
            ChartPanel grafico = graficoService.gerarGraficoComparativoCustoVenda(null);  // Passar dados adequados se necessário
            JFreeChart chart = grafico.getChart();

            // Cria a imagem do gráfico
            BufferedImage image = chart.createBufferedImage(800, 600);

            // Configura o response para enviar o gráfico em formato PNG
            response.setContentType("image/png");
            javax.imageio.ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o gráfico de custo vs. venda.");
        }
    }
}

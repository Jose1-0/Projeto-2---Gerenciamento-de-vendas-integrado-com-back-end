package com.visualizar.vendas.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RelatorioService {

	private final RestTemplate restTemplate;

	@Value("${uc06.url}")
	private String uc06Url;

	public RelatorioService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Map<String, Object>> getVendasMensais() {
		String url = uc06Url + "/api/vendas/vendas-mensais";
		return restTemplate.getForObject(url, List.class);
	}


	

	public List<Map<String, Object>> getQuantidadesMensais()  {
        // Criando dados mockados para teste
        List<Map<String, Object>> dadosMensais = new ArrayList<>();

        // Dados mockados: mês, quantidade comprada e quantidade vendida
        dadosMensais.add(Map.of(
            
       
"mes", "2024-01", 
            "quantidadeComprada", 1000, 
            "quantidadeVendida", 800));
        
        dadosMensais.add(Map.of(
            "mes", "2024-02", 
            "quantidadeComprada", 1200, 
            "quantidadeVendida", 900));
        
        dadosMensais.add(Map.of(
            "mes", "2024-03", 
            "quantidadeComprada", 1500, 
            "quantidadeVendida", 1400));
        
        dadosMensais.add(Map.of(
            "mes", "2024-04", 
            "quantidadeComprada", 1300, 
            "quantidadeVendida", 1000));
        
        dadosMensais.add(Map.of(
            "mes", "2024-05", 
            "quantidadeComprada", 1100, 
            "quantidadeVendida", 950));
        
        // Retorna os dados mockados
        return dadosMensais;
    }
}
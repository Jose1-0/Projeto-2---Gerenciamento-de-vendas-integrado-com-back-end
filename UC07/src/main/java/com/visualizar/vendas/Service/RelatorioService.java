package com.visualizar.vendas.Service;

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

    public List<Map<String, Object>> getQuantidadesMensais() {
        String url = uc06Url + "/api/vendas/quantidades-mensais";
        return restTemplate.getForObject(url, List.class);
    }
}
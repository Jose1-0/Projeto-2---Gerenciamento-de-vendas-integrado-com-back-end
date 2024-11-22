package com.cadastro.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro.vendas.repository.FuncionarioRepository;

@Service
public class AutenticacaoService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public boolean autenticar(String login, String senha) {
		
		return funcionarioRepository.findByLogin(login)
				.filter(funcionario -> funcionario.getSenha().equals(senha))
	            .map(funcionario -> funcionario.getPapel() != null) // Verifica se o papel não é nulo
	            .orElse(false);
	}
}

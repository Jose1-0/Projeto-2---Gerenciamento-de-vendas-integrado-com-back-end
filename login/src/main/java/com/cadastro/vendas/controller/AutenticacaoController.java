package com.cadastro.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.vendas.model.Funcionario;
import com.cadastro.vendas.repository.FuncionarioRepository;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@PostMapping("/login")
	public String login(@RequestBody Funcionario funcionario) {
		
        String login = funcionario.getLogin();
        String senha = funcionario.getSenha();
		
		try{
			boolean autenticado = funcionarioRepository.findByLogin(login)
					.filter(f -> f.getSenha().equals(senha))
                    .isPresent();
			
			return autenticado ? "Login bem sucedido!" : "Credenciais inválida.s";
		} catch(Exception e) {
			
			return "Ocorreu um erro ao tentar realizar o login. Tente novamente mais tarde ou entre em contato com forncedor do produto";
		}
		
	}
}

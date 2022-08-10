package com.algaworks.algafoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlgafoodApiApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(AlgafoodApiApplication.class, args);
//		CadastroCozinha cadastroCozinha = run.getBean(CadastroCozinha.class);
//
//		cadastroCozinha.listar().forEach(c -> System.out.println(c.getNome()));

	}


}

package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

//Exemplo de uma classe destinada a rodar a aplicação spring sem ser como aplicação web
public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //pega um bean do contexto do spring
        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        cadastroCozinha.listar().forEach(c -> System.out.println(c.getNome()));
        System.out.println(cadastroCozinha.buscarPorId(2l).getNome());
    }
}

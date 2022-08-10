package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

//Exemplo de uma classe destinada a rodar a aplicação spring sem ser como aplicação web
public class AlteracaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //pega um bean do contexto do spring
        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);


        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Brasileira");
        System.out.println(cadastroCozinha.salvar(cozinha));
    }
}

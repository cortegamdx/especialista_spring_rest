package com.algaworks.algafoodapi.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ListFormaPagamento {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run();

        FormaPagamentoRepository repository = context.getBean(FormaPagamentoRepository.class);

        repository.listar().forEach(System.out::println);
    }
}

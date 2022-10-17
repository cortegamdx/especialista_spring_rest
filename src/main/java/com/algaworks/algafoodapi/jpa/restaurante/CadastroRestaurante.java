package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class CadastroRestaurante {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run();

        RestauranteRepository repository = context.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Padaria do ze");
        restaurante.setTaxaFrete(BigDecimal.valueOf(4.50));

        repository.save(restaurante);
    }
}

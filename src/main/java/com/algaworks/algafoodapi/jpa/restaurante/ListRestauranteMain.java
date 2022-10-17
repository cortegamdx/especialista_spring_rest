package com.algaworks.algafoodapi.jpa.restaurante;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

public class ListRestauranteMain {
    public static void main(String[] args) {

        ApplicationContext springApplication = (ApplicationContext) new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run();

        RestauranteRepository repository = springApplication.getBean(RestauranteRepository.class);

        repository.findAll().forEach(System.out::println);

    }
}

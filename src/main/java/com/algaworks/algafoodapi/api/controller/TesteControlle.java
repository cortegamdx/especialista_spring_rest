package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteControlle {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping(value = "/{nomeCozinha}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Cozinha> cozinhaPorNome(@PathVariable("nomeCozinha") String nome){
        return cozinhaRepository.findByNome(nome);
    }
    @GetMapping(value = "/todas/{nomeCozinha}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> cozinhaPorNomeLike(@PathVariable("nomeCozinha") String nome){
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping(value="/restaurante/primeiro-por-nome")
    public Optional<Restaurante> restaurantePorNome(String nome){
        return  restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }
    @GetMapping(value="/restaurante/top2-por-nome")
    public List<Restaurante> restaurantesPorNomeTop2(String nome){
        return  restauranteRepository.findTop2ByNomeContaining(nome);
    }

}

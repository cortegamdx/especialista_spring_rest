package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService restauranteService;
    @Autowired
    private RestauranteRepository repository;

    @GetMapping
    public ResponseEntity<List<Restaurante>> todos() {
        return ResponseEntity.ok(restauranteService.todos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.porId(id));

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("por-taxa")
    public List<Restaurante> buscarPorTaxa(String nome,BigDecimal taxaInicial,BigDecimal taxaFinal){


        return repository.find(nome,taxaInicial,taxaFinal);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        Optional<Restaurante> restauranteAtual = repository.findById(id);

        if (!restauranteAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            restaurante = restauranteService.atualizar(restaurante, restauranteAtual.get());
            return ResponseEntity.ok().body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = restauranteService.porId(id);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(restauranteAtual, id);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper mapper = new ObjectMapper();
        //converte os valores dos campos recebidos para uma instancia da classe Restaurante
        Restaurante restauranteOrigem = mapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((campo, valor) -> {
            //Usando reflection para mapear os campos recebidos.
            Field field = ReflectionUtils.findField(Restaurante.class, campo);

            //transforma o atributo em public para poder acessar/alterar o valor.
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            //Para alterar o valor na classe
            ReflectionUtils.setField(field, restauranteDestino, novoValor);


        });
    }
}

package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;


    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        Optional<Estado> estado = repository.findById(id);

        if(estado.isPresent()){
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<Estado>> todos(){
        return  ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
        estado = cadastroEstadoService.adicionar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Estado estado, @PathVariable Long id) {
    try {
        estado = cadastroEstadoService.atualizar(estado, id);

        return ResponseEntity.ok(estado);
    }catch (EntidadeNaoEncontradaException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        try {
            cadastroEstadoService.remover(id);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}

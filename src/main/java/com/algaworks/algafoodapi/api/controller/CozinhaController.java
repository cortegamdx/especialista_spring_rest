package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.CozinhasXMLWrapper;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController                              // aceita json e xml em todos os metodos do controller
@RequestMapping(value = "/cozinhas")//,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    //aceita json e xml apenas nesse metodo
    @GetMapping//(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<Cozinha> listar() {
        return repository.listar();
    }

    //quando for xml vai chamar esse
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXMLWrapper listarXml() {
        return new CozinhasXMLWrapper(repository.listar());
    }


    //@ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable(name = "cozinhaId") Long id) {

        Cozinha cozinha = repository.buscar(id);
        if (cozinha != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        if (repository.buscarPorNome(cozinha.getNome()) == null) {

            cozinha = cadastroCozinhaService.salvar(cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id) {
        //cozinha.setId(id);
        // return ResponseEntity.ok(repository.salvar(cozinha));

        Cozinha cozinhaAtual = repository.buscar(id);

        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return ResponseEntity.ok(cadastroCozinhaService.salvar(cozinhaAtual));

        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cadastroCozinhaService.excluir(id);

            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {

            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

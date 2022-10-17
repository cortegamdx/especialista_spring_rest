package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com o codigo %d.", cozinhaId)));

        return repository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante, Restaurante restauranteAtual) {


        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com o codigo %d.", cozinhaId)));

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com o codigo %d.", cozinhaId));
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        restaurante = repository.save(restauranteAtual);

        return restaurante;
    }

    public List<Restaurante> todos() {
        return repository.findAll();
    }

    public Restaurante porId(Long id) {
        Restaurante restaurante = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante não cadastrado."));

        return restaurante;
    }
}

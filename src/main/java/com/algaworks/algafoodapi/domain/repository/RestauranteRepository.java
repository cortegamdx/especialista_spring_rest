package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RestauranteRepository {
    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante Restaurante);

    void remover(Restaurante restaurante);
}

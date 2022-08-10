package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> typeQuery = manager.createQuery("from Restaurante", Restaurante.class);
        List<Restaurante> restaurantes = typeQuery.getResultList();
        return restaurantes;
    }

    @Override
    public Restaurante buscar(Long id) {
        return  manager.find(Restaurante.class,id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    public void remover(Restaurante restaurante) {
        restaurante = this.buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}

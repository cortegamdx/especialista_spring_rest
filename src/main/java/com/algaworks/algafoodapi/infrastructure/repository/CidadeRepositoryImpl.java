package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Cidade> todas() {
        return manager.createQuery("from Cidade",Cidade.class).getResultList();
    }


    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class,id);
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade) {
         cidade = this.buscar(cidade.getId());
        manager.remove(cidade);
    }
}

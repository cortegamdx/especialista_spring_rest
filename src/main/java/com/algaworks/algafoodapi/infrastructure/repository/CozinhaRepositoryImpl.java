package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext //é um autowired para o entity manager, que pode passar algumas outras configuracoes
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {

        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);

        return query.getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return manager.createQuery("from Cozinha where nome = :nome",Cozinha.class)
                .setParameter("nome",nome)
                .getResultList();
    }


    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);

        if(cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cozinha);
    }

    @Override
    public Cozinha buscarPorNome(String nome) {
        TypedQuery<Cozinha> query = manager.createQuery("SELECT c FROM Cozinha c WHERE c.nome = :nome", Cozinha.class);
      try {

        Cozinha cozinha = query.setParameter("nome", nome).getSingleResult();
        return cozinha;

      }catch (NoResultException | NonUniqueResultException e){
          return null;
      }

    }
}

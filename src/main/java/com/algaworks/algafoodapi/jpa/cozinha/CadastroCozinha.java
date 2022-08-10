package com.algaworks.algafoodapi.jpa.cozinha;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext //é um autowired para o entity manager, que pode passar algumas outras configuracoes
    private EntityManager manager;


    public List<Cozinha> listar(){

        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);

        return query.getResultList();
        
    }

    public Cozinha buscarPorId(Long id){
        return  manager.find(Cozinha.class,id);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
      return manager.merge(cozinha);
    }

    @Transactional
    public void remover(Cozinha cozinha){
        cozinha = buscarPorId(cozinha.getId());
        manager.remove(cozinha);
    }
}

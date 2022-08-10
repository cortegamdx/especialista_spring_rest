package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> todas();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}

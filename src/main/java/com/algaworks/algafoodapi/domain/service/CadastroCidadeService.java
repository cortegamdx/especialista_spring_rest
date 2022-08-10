package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade adicionar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        try {
            Estado estado = estadoRepository.buscar(estadoId);

            return cidadeRepository.salvar(cidade);
        } catch (NullPointerException | EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um estado cadastrado com o codigo %d", estadoId));
        }
    }

    public Cidade atualizar(Cidade cidade, Long id) {

        Cidade cidadeAtual = cidadeRepository.buscar(id);
        Long estadoId = cidade.getEstado().getId();

        if (cidadeAtual == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe uma cidade cadastrada com o codigo %d.", id));
        }

        Estado estado = estadoRepository.buscar(estadoId);
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe nenhum estado cadastrado com o codigo %d.", estadoId));
        }

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        cidade = cidadeRepository.salvar(cidadeAtual);


        return cidade;
    }

    public void remover(Long id) {
        Cidade cidade = cidadeRepository.buscar(id);
        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe uma cidade cadastrada com o codigo %d.", id));
        }

        cidadeRepository.remover(cidade);
    }

    public Cidade buscar(Long id){
        Cidade cidade = cidadeRepository.buscar(id);
        if(cidade == null){
            throw new EntidadeNaoEncontradaException(String.format("Não existe uma cidade cadastrada com o codigo %d.", id));
        }
        return cidade;
    }
}

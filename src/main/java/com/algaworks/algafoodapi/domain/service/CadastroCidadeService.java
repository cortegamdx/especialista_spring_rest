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


        estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um estado cadastrado com o codigo %d", estadoId)));

        cidade = cidadeRepository.save(cidade);

        return cidade;

    }

    public Cidade atualizar(Cidade cidade, Long id) {

        Cidade cidadeAtual = cidadeRepository.findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException(
                                String.format("Não existe uma cidade cadastrada com o codigo %d.", id)));

        Long estadoId = cidade.getEstado().getId();



        estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um estado cadastrado com o codigo %d", estadoId)));


        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        cidade = cidadeRepository.save(cidadeAtual);


        return cidade;
    }

    public void remover(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe uma cidade cadastrada com o codigo %d.", id)));

        cidadeRepository.delete(cidade);
    }

    public Cidade buscar(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe uma cidade cadastrada com o codigo %d.", id)));
        return cidade;
    }
}

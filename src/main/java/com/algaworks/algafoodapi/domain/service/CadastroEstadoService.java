package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;


    public Estado adicionar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Estado estado, Long id) {

        Estado estadoAtual = estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um estado cadastrado para o codigo %d", id)));

        BeanUtils.copyProperties(estado, estadoAtual, "id");
        estado = estadoRepository.save(estadoAtual);

        return estado;
    }

    public void remover(Long id) {
        try {
            Estado estado = estadoRepository.findById(id).get();

            estadoRepository.delete(estado);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("O estado de codigo %d não pode ser deletado pois está em uso.", id));
        } catch (NullPointerException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe um estado cadastrado para o codigo %d", id));
        }
    }
}

package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.entity.TipoEventoEntity;
import org.acme.repository.TipoEventoRepository;

import java.util.List;

@ApplicationScoped
public class TipoEventoService {

  @Inject
  TipoEventoRepository tipoEventoRepository;

  public List<TipoEventoEntity> listAll() {
    return tipoEventoRepository.listAll();
  }

  public TipoEventoEntity findById(Long id) {
    return tipoEventoRepository.findByIdOptional(id)
        .orElseThrow(() -> new NotFoundException("TipoEvento não encontrado com id: " + id));
  }

  @Transactional
  public TipoEventoEntity create(TipoEventoEntity tipoEvento) {
    tipoEventoRepository.persist(tipoEvento);
    return tipoEvento;
  }

  @Transactional
  public TipoEventoEntity update(Long id, TipoEventoEntity tipoEventoData) {
    TipoEventoEntity entity = findById(id);
    entity.nomeTipo = tipoEventoData.nomeTipo;
    entity.descricao = tipoEventoData.descricao;
    // tipoEventoRepository.persist(entity) is not strictly necessary for managed
    // entities
    return entity;
  }

  @Transactional
  public void delete(Long id) {
    TipoEventoEntity entity = findById(id);
    tipoEventoRepository.delete(entity);
  }
}
package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.entity.OcorrenciaEntity;
import org.acme.entity.TipoEventoEntity;
import org.acme.repository.OcorrenciaRepository;
import org.acme.repository.TipoEventoRepository;

import java.util.List;

@ApplicationScoped
public class OcorrenciaService {

  @Inject
  OcorrenciaRepository ocorrenciaRepository;

  @Inject
  TipoEventoRepository tipoEventoRepository;

  public List<OcorrenciaEntity> listAll() {
    return ocorrenciaRepository.listAll();
  }

  public OcorrenciaEntity findById(Long id) {
    return ocorrenciaRepository.findByIdOptional(id)
        .orElseThrow(() -> new NotFoundException("Ocorrência não encontrada com id: " + id));
  }

  @Transactional
  public OcorrenciaEntity create(OcorrenciaEntity ocorrencia) {
    if (ocorrencia.tipoEvento != null && ocorrencia.tipoEvento.idTipoEvento != null) {
      TipoEventoEntity tipo = tipoEventoRepository.findByIdOptional(ocorrencia.tipoEvento.idTipoEvento)
          .orElseThrow(
              () -> new NotFoundException("TipoEvento não encontrado com id: " + ocorrencia.tipoEvento.idTipoEvento));
      ocorrencia.tipoEvento = tipo;
    } else {
      throw new IllegalArgumentException("ID do TipoEvento é obrigatório para criar uma Ocorrência.");
    }
    ocorrenciaRepository.persist(ocorrencia);
    return ocorrencia;
  }

  @Transactional
  public OcorrenciaEntity update(Long id, OcorrenciaEntity ocorrenciaData) {
    OcorrenciaEntity entity = findById(id);

    entity.nomeEvento = ocorrenciaData.nomeEvento;
    if (ocorrenciaData.tipoEvento != null && ocorrenciaData.tipoEvento.idTipoEvento != null) {
      TipoEventoEntity tipo = tipoEventoRepository.findByIdOptional(ocorrenciaData.tipoEvento.idTipoEvento)
          .orElseThrow(() -> new NotFoundException(
              "TipoEvento não encontrado com id: " + ocorrenciaData.tipoEvento.idTipoEvento));
      entity.tipoEvento = tipo;
    }
    entity.dataHoraInicio = ocorrenciaData.dataHoraInicio;
    entity.dataHoraFim = ocorrenciaData.dataHoraFim;
    entity.latitude = ocorrenciaData.latitude;
    entity.longitude = ocorrenciaData.longitude;
    entity.intensidade = ocorrenciaData.intensidade;
    entity.fonteDados = ocorrenciaData.fonteDados;
    entity.linkOriginal = ocorrenciaData.linkOriginal;
    entity.statusEvento = ocorrenciaData.statusEvento;
    entity.areaAfetada = ocorrenciaData.areaAfetada;

    return entity;
  }

  @Transactional
  public void delete(Long id) {
    OcorrenciaEntity entity = findById(id);
    ocorrenciaRepository.delete(entity);
  }
}
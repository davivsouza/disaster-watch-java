package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.TipoEventoEntity;

@ApplicationScoped
public class TipoEventoRepository implements PanacheRepository<TipoEventoEntity> {
  // You can add custom query methods here if needed
  // For example: public TipoEventoEntity findByNomeTipo(String nomeTipo) {
  // return find("nomeTipo", nomeTipo).firstResult();
  // }
}
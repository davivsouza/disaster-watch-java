package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.OcorrenciaEntity;
// import java.util.List; // If adding custom methods returning List

@ApplicationScoped
public class OcorrenciaRepository implements PanacheRepository<OcorrenciaEntity> {
  // Example custom query:
  // public List<OcorrenciaEntity> findByStatusEvento(String status) {
  // return list("statusEvento", status);
  // }
}
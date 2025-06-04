package org.acme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DW_TipoEvento")
public class TipoEventoEntity {
  // Example for TipoEventoEntity using IDENTITY
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_tipo_evento")
  public Long idTipoEvento;
  
  @Column(name = "nome_tipo", nullable = false, unique = true, length = 100)
  public String nomeTipo;

  @Column(name = "descricao", length = 500)
  public String descricao;

  // Getters and Setters (optional with Panache, but good practice)
  // Constructors
}
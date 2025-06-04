package org.acme.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // For NUMBER(10,6)
import java.time.LocalDateTime; // For TIMESTAMP

@Entity
@Table(name = "DW_Ocorrencia")
public class OcorrenciaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // <<< ALTERAÇÃO AQUI
  @Column(name = "id_ocorrencia")
  public Long idOcorrencia;

  @Column(name = "nome_evento", nullable = false, length = 255)
  public String nomeEvento;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tipo_evento", nullable = false)
  public TipoEventoEntity tipoEvento;

  @Column(name = "data_hora_inicio", nullable = false)
  public LocalDateTime dataHoraInicio;

  @Column(name = "data_hora_fim")
  public LocalDateTime dataHoraFim;

  @Column(name = "latitude", nullable = false, precision = 10, scale = 6)
  public BigDecimal latitude;

  @Column(name = "longitude", nullable = false, precision = 10, scale = 6)
  public BigDecimal longitude;

  @Column(name = "intensidade", length = 100)
  public String intensidade;

  @Column(name = "fonte_dados", nullable = false, length = 255)
  public String fonteDados;

  @Column(name = "link_original", length = 500)
  public String linkOriginal;

  @Column(name = "status_evento", nullable = false, length = 50)
  public String statusEvento;

  @Column(name = "area_afetada", length = 500)
  public String areaAfetada;

  // Getters and Setters
  // Constructors
}
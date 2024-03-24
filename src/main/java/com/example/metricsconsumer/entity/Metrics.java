package com.example.metricsconsumer.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;


/**
 * Сущность метрика
 */

@Entity
@Table(name = "metrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Metrics {

  /* Идентификатор метрики */
  @Id
  @UuidGenerator(style = UuidGenerator.Style.TIME)
  private UUID id;

  /* Наименование метрики */
  @Column(name = "name", updatable = false, nullable = false)
  private String name;

  /* Описание метрики */
  @Column(name = "description", updatable = false, nullable = false)
  private String description;

  /* Измерения метрики */
  @Column(name = "measurements", updatable = false, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Measurement measurements;

  /* Дата создания метрики */
  @Column(name = "created_at", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp createdAt;

  /* Внутренний класс Измерения */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Measurement implements Serializable {
    private JsonNode measurements;
  }
}




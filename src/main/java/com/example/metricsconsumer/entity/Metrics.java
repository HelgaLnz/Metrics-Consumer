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

@Entity
@Table(name = "metrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Metrics {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.TIME)
  private UUID id;

  @Column(name = "name", updatable = false, nullable = false)
  private String name;

  @Column(name = "description", updatable = false, nullable = false)
  private String description;

  @Column(name = "measurements", updatable = false, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Measurement measurements;

  @Column(name = "created_at", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp createdAt;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Measurement implements Serializable {
    private JsonNode measurements;
  }
}




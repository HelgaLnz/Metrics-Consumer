package com.example.metricsconsumer.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * DTO метрик
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricsDto {

  /* Наименование метрики*/
  private String name;

  /* Описание метрики */
  private String description;

  /* Измерения */
  private JsonNode measurements;

  /* Дата и время метрики */
  private Timestamp createdAt;

}

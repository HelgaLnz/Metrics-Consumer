package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricsDto;
import com.example.metricsconsumer.entity.Metrics;
import com.example.metricsconsumer.repository.MetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для метрик
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsService {

  private final MetricsRepository metricsRepository;

  /* Сохранить метрики */
  public void saveMetrics(MetricsDto metricsKafka) {
    metricsRepository.save(Metrics.builder()
      .name(metricsKafka.getName())
      .description(metricsKafka.getDescription())
      .measurements(new Metrics.Measurement(metricsKafka.getMeasurements()))
      .createdAt(metricsKafka.getCreatedAt())
      .build());
    log.info(metricsKafka.toString());
  }

  /* Получить все метрики */
  public Page<MetricsDto> getAll(PageRequest pageable) {
    return metricsRepository.findAll(pageable).map(metrics -> MetricsDto.builder()
      .name(metrics.getName())
      .description(metrics.getDescription())
      .measurements(metrics.getMeasurements().getMeasurements())
      .createdAt(metrics.getCreatedAt())
      .build());
  }

  /* Получить метрики по id */
  public MetricsDto getById(UUID id) {
    return metricsRepository.findById(id).map(metrics -> MetricsDto.builder()
        .name(metrics.getName())
        .description(metrics.getDescription())
        .measurements(metrics.getMeasurements().getMeasurements())
        .createdAt(metrics.getCreatedAt())
        .build())
      .orElseThrow();
  }

  /* Получить метрики по диапазону timestamp */
  public List<MetricsDto> getMetricsByCreatedAt(Long from, Long to) {
    return metricsRepository.getByCreatedAtBetween(
        new Date(from),
        new Date(to)
      )
      .stream()
      .map(metrics -> MetricsDto.builder()
        .name(metrics.getName())
        .description(metrics.getDescription())
        .measurements(metrics.getMeasurements().getMeasurements())
        .createdAt(metrics.getCreatedAt())
        .build())
      .toList();
  }
}

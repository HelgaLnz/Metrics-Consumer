package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricsDto;
import com.example.metricsconsumer.entity.Metrics;
import com.example.metricsconsumer.repository.MetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetricsService {

  private final MetricsRepository metricsRepository;

  public void saveMetrics(MetricsDto metricsKafka) {
    metricsRepository.save(Metrics.builder()
      .name(metricsKafka.getName())
      .description(metricsKafka.getDescription())
      .measurements(new Metrics.Measurement(metricsKafka.getMeasurements()))
      .createdAt(metricsKafka.getCreatedAt())
      .build());
    log.info(metricsKafka.toString());
  }

  public Page<MetricsDto> getAll(PageRequest pageable) {
    return metricsRepository.findAll(pageable).map(metrics -> MetricsDto.builder()
      .name(metrics.getName())
      .description(metrics.getDescription())
      .measurements(metrics.getMeasurements().getMeasurements())
      .createdAt(metrics.getCreatedAt())
      .build());
  }

  public MetricsDto getById(UUID id) {
    return metricsRepository.findById(id).map(metrics -> MetricsDto.builder()
        .name(metrics.getName())
        .description(metrics.getDescription())
        .measurements(metrics.getMeasurements().getMeasurements())
        .createdAt(metrics.getCreatedAt())
        .build())
      .orElseThrow();
  }
}

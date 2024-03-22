package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

  private final MetricsService metricsService;

  @KafkaListener(topics = "metrics-topic", groupId = "group-metrics")
  public void consume(MetricsDto metrics) {
    log.info(String.format("Consuming metrics -> %s", metrics));
    metricsService.saveMetrics(metrics);
  }
}

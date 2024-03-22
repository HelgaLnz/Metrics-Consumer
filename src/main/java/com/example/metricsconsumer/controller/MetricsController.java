package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.MetricsDto;
import com.example.metricsconsumer.entity.Metrics;
import com.example.metricsconsumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MetricsController {

  private final MetricsService metricsService;

  @GetMapping("/metrics")
  public ResponseEntity<Page<MetricsDto>> getAllMetrics(
    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") Integer limit
  ) {
    return new ResponseEntity<>(metricsService.getAll(PageRequest.of(offset, limit)), HttpStatus.OK);
  }

  @GetMapping("/metrics/{id}")
  public ResponseEntity<?> getById(@PathVariable UUID id) {
    return new ResponseEntity<>(metricsService.getById(id), HttpStatus.OK);
  }
}

package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.entity.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, UUID> {

  @Query("select m from Metrics m where m.createdAt between :createdAtFrom and :createdAtTo")
  List<Metrics> getByCreatedAtBetween(Date createdAtFrom, Date createdAtTo);
}

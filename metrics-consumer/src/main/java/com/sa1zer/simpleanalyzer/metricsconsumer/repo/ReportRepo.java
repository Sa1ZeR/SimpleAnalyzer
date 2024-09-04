package com.sa1zer.simpleanalyzer.metricsconsumer.repo;

import com.sa1zer.simpleanalyzer.metricsconsumer.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report, Long> {
}

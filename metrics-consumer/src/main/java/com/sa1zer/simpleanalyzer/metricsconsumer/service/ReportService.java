package com.sa1zer.simpleanalyzer.metricsconsumer.service;

import com.sa1zer.simpleanalyzer.metricsconsumer.entity.Report;
import com.sa1zer.simpleanalyzer.metricsconsumer.repo.ReportRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepo repo;

    @Transactional(readOnly = true)
    public List<Report> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Report findById(Long id) {
        return repo.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));
    }

    @Transactional
    public Report save(Report report) {
        return repo.save(report);
    }
}

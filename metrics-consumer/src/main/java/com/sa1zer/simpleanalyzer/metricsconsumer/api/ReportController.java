package com.sa1zer.simpleanalyzer.metricsconsumer.api;

import com.sa1zer.simpleanalyzer.metricsconsumer.service.ReportService;
import com.sa1zer.simpleanalyzer.metricsconsumer.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metrics/")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("{id}")
    public Report report(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @GetMapping("all")
    public List<Report> findAll() {
        return reportService.findAll();
    }
}

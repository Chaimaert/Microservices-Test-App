package com.rapport.generator.controller;

import com.rapport.generator.model.Report;
import com.rapport.generator.model.TestResult;
import com.rapport.generator.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class RapportController {

    private final ReportService reportService;

    public RapportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport(@RequestBody TestResult testResult) {
        String reportPath = reportService.generateReport(testResult);
        return ResponseEntity.ok("Report generated: " + reportPath);
    }

    @PostMapping("/from-test-generator")
    public ResponseEntity<String> generateReportFromTestResults(@RequestBody List<TestResult> testResults) {
        String reportPath = reportService.generateReportFromTestResults(testResults);
        return ResponseEntity.ok("Report generated: " + reportPath);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<Report>> listAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        File file = new File(report.getFilePath());

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + report.getFilePath());
        }

        try {
            byte[] fileContent = java.nio.file.Files.readAllBytes(file.toPath());
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + report.getReportName() + "\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(fileContent);

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + report.getFilePath(), e);
        }
    }
}

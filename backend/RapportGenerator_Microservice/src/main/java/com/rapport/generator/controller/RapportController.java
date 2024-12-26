package com.rapport.generator.controller;

import com.rapport.generator.model.Report;
import com.rapport.generator.model.TestScenario;
import com.rapport.generator.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class RapportController {

    private final ReportService reportService;

    public RapportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Generate comprehensive report directly from user-provided code
    @PostMapping("/generate-from-code")
    public ResponseEntity<String> generateReportFromCode(@RequestBody String code) {
        String reportPath = reportService.generateComprehensiveReport(code);
        return ResponseEntity.ok("Report generated successfully at: " +reportPath);
    }

    // List all generated reports
    @GetMapping
    public ResponseEntity<List<Report>> listAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // Download a specific report by ID
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

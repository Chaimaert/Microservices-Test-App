package com.rapport.generator.service;

import com.rapport.generator.model.Report;
import com.rapport.generator.model.TestResult;
import com.rapport.generator.repository.ReportRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {

    @Value("${report.file.storage}")
    private String fileStoragePath;

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public String generateReport(TestResult testResult) {
        String reportName = "Report_" + UUID.randomUUID() + ".pdf";
        String filePath = saveReportToFile(List.of(testResult), reportName);
        saveReportMetadata(reportName, filePath);
        return filePath;
    }

    public String generateReportFromTestResults(List<TestResult> testResults) {
        String reportName = "JUnit_Report_" + UUID.randomUUID() + ".pdf";
        String filePath = saveReportToFile(testResults, reportName);
        saveReportMetadata(reportName, filePath);
        return filePath;
    }

    private String saveReportToFile(List<TestResult> testResults, String reportName) {
        String filePath = fileStoragePath + "/" + reportName;

        File directory = new File(fileStoragePath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create the directory: " + fileStoragePath);
        }

        try {
            generatePDFFromTestResults(testResults, filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF report", e);
        }

        return filePath;
    }

    private void saveReportMetadata(String reportName, String filePath) {
        Report report = new Report();
        report.setReportName(reportName);
        report.setGeneratedTime(LocalDateTime.now().toString());
        report.setReportFormat("PDF");
        report.setFilePath(filePath);

        reportRepository.save(report);
    }

    private void generatePDFFromTestResults(List<TestResult> testResults, String filePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        try {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 700);

            contentStream.showText("JUnit Test Report");
            contentStream.newLineAtOffset(0, -20);

            for (TestResult result : testResults) {
                contentStream.showText("Test Name: " + result.getTestName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Status: " + (result.isSuccess() ? "Passed" : "Failed"));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Execution Time: " + result.getExecutionTime());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Details: " + result.getDetails());
                contentStream.newLineAtOffset(0, -40);
            }

            contentStream.endText();
        } finally {
            contentStream.close();
        }

        document.save(filePath);
        document.close();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}

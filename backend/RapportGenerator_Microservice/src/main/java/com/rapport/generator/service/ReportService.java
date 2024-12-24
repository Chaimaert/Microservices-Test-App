package com.rapport.generator.service;

import com.rapport.generator.model.Report;
import com.rapport.generator.model.TestScenario;
import com.rapport.generator.repository.ReportRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rapport.generator.exception.ReportGenerationException;
import com.rapport.generator.exception.TestScenarioFetchException;
import com.rapport.generator.exception.ReportNotFoundException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Value("${report.file.storage}")
    private String fileStoragePath;

    @Value("${test.generator.url}")
    private String testGeneratorUrl;

    private final ReportRepository reportRepository;
    private final MessageSource messageSource;

    public ReportService(ReportRepository reportRepository, MessageSource messageSource) {
        this.reportRepository = reportRepository;
        this.messageSource = messageSource;
    }

    private String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    // Fetch Test Scenarios from Test Generator Microservice
    public List<TestScenario> fetchTestScenarios(String code) {
        logger.info("Fetching test scenarios for code: {}", code);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(code, headers);

        ResponseEntity<TestScenario[]> response = restTemplate.postForEntity(
                testGeneratorUrl, request, TestScenario[].class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new TestScenarioFetchException(getMessage("error.fetch.test.scenarios"));
        }
    }

    // Generate Report from Test Scenarios
    public String generateReportFromTestResults(List<TestScenario> testScenarios) {
        logger.info("Generating report from test scenarios");
        String reportName = "JUnit_Report_" + UUID.randomUUID() + ".pdf";
        String filePath = saveReportToFile(testScenarios, reportName);
        saveReportMetadata(reportName, filePath);
        return filePath;
    }

    private String saveReportToFile(List<TestScenario> testScenarios, String reportName) {
        String filePath = fileStoragePath + "/" + reportName;

        File directory = new File(fileStoragePath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new ReportGenerationException(getMessage("error.create.directory", fileStoragePath));
        }

        try {
            generatePDFFromTestScenarios(testScenarios, filePath);
        } catch (IOException e) {
            throw new ReportGenerationException(getMessage("error.generate.pdf"), e);
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

    private void generatePDFFromTestScenarios(List<TestScenario> testScenarios, String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);

                contentStream.showText("JUnit Test Report");
                contentStream.newLineAtOffset(0, -20);

                for (TestScenario scenario : testScenarios) {
                    contentStream.showText("Test Name: " + scenario.getTestName());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Content: " + scenario.getContent());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Status: " + scenario.getStatus());
                    contentStream.newLineAtOffset(0, -40);
                }

                contentStream.endText();
            }

            document.save(filePath);
        }
    }

    public Report getReportById(Long id) {
        logger.info("Fetching report by ID: {}", id);
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(getMessage("error.report.not.found", id)));
    }

    public List<Report> getAllReports() {
        logger.info("Fetching all reports");
        return reportRepository.findAll();
    }
}

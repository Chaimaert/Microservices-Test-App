package com.example.codeanalyzer.controller;

import com.example.codeanalyzer.model.CodeAnalysisResult;
import com.example.codeanalyzer.service.CodeAnalyzerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyse")
public class CodeAnalyzerController {

    private final CodeAnalyzerService codeAnalyzerService;

    public CodeAnalyzerController(CodeAnalyzerService codeAnalyzerService) {
        this.codeAnalyzerService = codeAnalyzerService;
    }

    @PostMapping("/code")
    public CodeAnalysisResult analyzeCode(@RequestBody String code) {
        return codeAnalyzerService.analyzeCode(code);
    }
}
package com.example.codeanalysis.controller;

import com.example.codeanalysis.model.TestScenario;
import com.example.codeanalysis.service.CodeAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code-analysis")
public class CodeAnalysisController {

    private final CodeAnalysisService codeAnalysisService;

    public CodeAnalysisController(CodeAnalysisService codeAnalysisService) {
        this.codeAnalysisService = codeAnalysisService;
    }

    @PostMapping("/analyze")
    public List<TestScenario> analyzeCode(@RequestBody String code) {
        return codeAnalysisService.analyzeCode(code);
    }
}
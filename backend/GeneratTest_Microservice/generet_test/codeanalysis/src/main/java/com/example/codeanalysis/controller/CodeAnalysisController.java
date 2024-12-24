package com.example.codeanalysis.controller;

import com.example.codeanalysis.model.TestScenario;
import com.example.codeanalysis.service.CodeAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class CodeAnalysisController {

    private final CodeAnalysisService codeAnalysisService;

    public CodeAnalysisController(CodeAnalysisService codeAnalysisService) {
        this.codeAnalysisService = codeAnalysisService;
    }


    @PostMapping("/generate_code")
    public ResponseEntity<List<TestScenario>> analyzeCode(@RequestBody String code) {
        List<TestScenario> scenarios = codeAnalysisService.analyzeCode(code);
        return ResponseEntity.ok(scenarios);
    }
}
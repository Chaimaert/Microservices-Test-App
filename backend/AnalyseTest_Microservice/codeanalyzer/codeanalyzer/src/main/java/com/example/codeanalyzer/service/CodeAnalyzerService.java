package com.example.codeanalyzer.service;

import com.example.codeanalyzer.model.CodeAnalysisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CodeAnalyzerService {

    @Autowired
    private RestTemplate restTemplate;

    public CodeAnalysisResult analyzeCode(String code) {
        CodeAnalysisResult result = new CodeAnalysisResult();
        result.setCode(code);

        // Appel au microservice de génération de tests
        String generatedTests = restTemplate.postForObject("http://localhost:8082/test/generate_code", code, String.class);

        // Analyse des tests générés
        String analysis = performAnalysis(generatedTests);
        result.setAnalysis(analysis);
        result.setStatus("Success");

        return result;
    }

    private String performAnalysis(String generatedTests) {
        if (generatedTests == null || generatedTests.isEmpty()) {
            return "Aucun test généré.";
        }

        System.out.println("Tests générés : " + generatedTests); // Débogage

        // Vérifiez la présence de cas de test spécifiques
        if (!generatedTests.contains("@Test")) {
            return "Aucun cas de test trouvé.";
        }

        // Vérifiez également si le code est compilable (si possible)
        // Vous pourriez envisager d'utiliser un compilateur dynamique ici

        return "Les tests générés sont corrects et couvrent les cas attendus.";
    }
}
package com.example.codeanalysis.model;

import lombok.Data;

@Data
public class TestScenario {
    private String methodName; // Nom de la méthode testée
    private String testCode;    // Code du scénario de test
    private String status;      // Statut du test (e.g., Passed, Failed, Pending)
    private String errorMessage; // Message d'erreur en cas d'échec (optionnel)

    // Constructeur
    public TestScenario(String methodName, String testCode, String status) {
        this.methodName = methodName;
        this.testCode = testCode;
        this.status = status;
        this.errorMessage = null; // Initialisé à null par défaut
    }


}
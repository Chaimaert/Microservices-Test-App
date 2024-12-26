package com.rapport.generator.model;

public class TestScenario {
    private String methodName;
    private String testCode;
    private String status;
    private String errorMessage;

    // Constructeur
    public TestScenario(String methodName, String testCode, String status, String errorMessage) {
        this.methodName = methodName;
        this.testCode = testCode;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    // Getters et Setters
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "TestScenario{" +
                "methodName='" + methodName + '\'' +
                ", testCode='" + testCode + '\'' +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}